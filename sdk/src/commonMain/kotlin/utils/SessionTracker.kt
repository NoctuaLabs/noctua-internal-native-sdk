package com.noctuagames.labs.sdk.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.concurrent.Volatile
import kotlin.coroutines.CoroutineContext
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Interface for receiving session lifecycle events from [SessionTracker].
 * Decouples session tracking from the presenter for testability.
 */
internal interface SessionEventSink {
    fun trackCustomEvent(eventName: String, properties: Map<String, Any>)
}

data class SessionTrackerConfig(
    val heartbeatPeriodMs: Long = 60_000,  // 60 seconds
    val sessionTimeoutMs: Long = 300_000   // 5 minutes
)

@OptIn(ExperimentalUuidApi::class)
internal class SessionTracker(
    private val config: SessionTrackerConfig,
    private val eventSink: SessionEventSink,
    // Gated by noctua.nativeInternalTrackerEnabled. When true, a
    // noctua_user_engagement event is emitted before each session event.
    private val userEngagementEnabled: Boolean = false,
    coroutineContext: CoroutineContext = Dispatchers.Default,
    private val timeProvider: () -> Long = ::getCurrentTimeMillis
) : CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = coroutineContext + job

    @Volatile private var sessionId: String? = null
    @Volatile private var nextHeartbeat = timeProvider() + config.heartbeatPeriodMs
    @Volatile private var nextSessionTimeout = 0L
    @Volatile private var isPaused = true
    @Volatile private var isDisposed = false

    // Timestamp of the last engagement send; used to compute the incremental
    // foreground time (engagement_time_msec) reported with each engagement event.
    @Volatile private var engagementMark = timeProvider()

    private var heartbeatJob: Job? = null

    init {
        startHeartbeatLoop()
    }

    fun onApplicationPause(pauseStatus: Boolean) {
        if (isPaused == pauseStatus) return
        isPaused = pauseStatus

        if (pauseStatus) {
            // Foreground time accrued since the last send, then the pause partner.
            emitEngagement(EngagementLifecycle.PAUSE, foregroundElapsed = true)
            eventSink.trackCustomEvent("session_pause", emptyMap())
            nextSessionTimeout = timeProvider() + config.sessionTimeoutMs
            return
        }

        // Resume
        if (sessionId != null && timeProvider() >= nextSessionTimeout) {
            // Expired session → reset
            sessionId = null
        }

        if (sessionId != null) {
            // Resuming an active session: the gap since pause was background, so 0.
            emitEngagement(EngagementLifecycle.FOREGROUND, foregroundElapsed = false)
            eventSink.trackCustomEvent("session_continue", emptyMap())
        } else {
            sessionId = Uuid.random().toString()
            ExperimentManager.setSessionId(sessionId!!)
            emitEngagement(EngagementLifecycle.START, foregroundElapsed = false)
            eventSink.trackCustomEvent("session_start", emptyMap())
        }
    }

    private fun startHeartbeatLoop() {
        heartbeatJob = launch {
            while (isActive && !isDisposed) {
                if (isPaused) {
                    // Backgrounded: no heartbeats. Re-check after one period
                    // instead of waking the CPU 10×/second.
                    delay(config.heartbeatPeriodMs)
                    continue
                }

                val now = timeProvider()
                if (now >= nextHeartbeat) {
                    emitEngagement(EngagementLifecycle.FOREGROUND, foregroundElapsed = true)
                    eventSink.trackCustomEvent("session_heartbeat", emptyMap())
                    nextHeartbeat = now + config.heartbeatPeriodMs
                }

                // Sleep until the next heartbeat is due (capped at one period so a
                // clock jump or resume can't make us oversleep).
                val sleepMs = (nextHeartbeat - timeProvider())
                    .coerceIn(1, config.heartbeatPeriodMs)
                delay(sleepMs)
            }
        }
    }

    fun dispose() {
        if (isDisposed) return
        isDisposed = true
        // If we're already paused, foreground time was counted up to the pause.
        emitEngagement(EngagementLifecycle.END, foregroundElapsed = !isPaused)
        eventSink.trackCustomEvent("session_end", emptyMap())
        job.cancel()
        heartbeatJob?.cancel()
    }

    /**
     * Emits a [noctua_user_engagement] event *before* its session-event partner,
     * mirroring the Unity SDK. [engagement_time_msec] is the incremental
     * foreground time since the last send (0 on start/resume, where the preceding
     * interval was either nothing or background).
     */
    private fun emitEngagement(lifecycle: EngagementLifecycle, foregroundElapsed: Boolean) {
        val now = timeProvider()
        val engagementMs = if (foregroundElapsed) (now - engagementMark).coerceAtLeast(0) else 0L
        engagementMark = now

        if (!userEngagementEnabled) return

        eventSink.trackCustomEvent(
            "noctua_user_engagement",
            mapOf(
                "engagement_time_msec" to engagementMs,
                "lifecycle" to lifecycle.value
            )
        )
    }

    private enum class EngagementLifecycle(val value: String) {
        START("start"),
        FOREGROUND("foreground"),
        PAUSE("pause"),
        END("end")
    }
}
