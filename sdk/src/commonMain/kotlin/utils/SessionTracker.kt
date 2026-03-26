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

    private var heartbeatJob: Job? = null

    init {
        startHeartbeatLoop()
    }

    fun onApplicationPause(pauseStatus: Boolean) {
        if (isPaused == pauseStatus) return
        isPaused = pauseStatus

        if (pauseStatus) {
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
            eventSink.trackCustomEvent("session_continue", emptyMap())
        } else {
            sessionId = Uuid.random().toString()
            ExperimentManager.setSessionId(sessionId!!)
            eventSink.trackCustomEvent("session_start", emptyMap())
        }
    }

    private fun startHeartbeatLoop() {
        heartbeatJob = launch {
            while (isActive && !isDisposed) {
                if (!isPaused && timeProvider() >= nextHeartbeat) {
                    eventSink.trackCustomEvent("session_heartbeat", emptyMap())
                    nextHeartbeat = timeProvider() + config.heartbeatPeriodMs
                }
                delay(100)
            }
        }
    }

    fun dispose() {
        if (isDisposed) return
        isDisposed = true
        eventSink.trackCustomEvent("session_end", emptyMap())
        job.cancel()
        heartbeatJob?.cancel()
    }
}
