package com.noctuagames.labs.sdk.utils

import com.noctuagames.labs.sdk.presenter.NoctuaInternalPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class SessionTrackerConfig(
    val heartbeatPeriodMs: Long = 60_000,  // 60 seconds
    val sessionTimeoutMs: Long = 300_000   // 5 minutes
)

@OptIn(ExperimentalUuidApi::class)
internal class SessionTracker(
    private val config: SessionTrackerConfig,
    private val presenter: NoctuaInternalPresenter
) : CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext = Dispatchers.Default + job

    private var sessionId: String? = null
    private var nextHeartbeat = getCurrentTimeMillis() + config.heartbeatPeriodMs
    private var nextSessionTimeout = 0L
    private var isPaused = true
    private var isDisposed = false

    private var heartbeatJob: Job? = null

    init {
        startHeartbeatLoop()
    }

    fun onApplicationPause(pauseStatus: Boolean) {
        if (isPaused == pauseStatus) return
        isPaused = pauseStatus

        if (pauseStatus) {
            presenter.trackCustomEvent("session_pause", emptyMap())
            nextSessionTimeout = getCurrentTimeMillis() + config.sessionTimeoutMs
            return
        }

        // Resume
        if (sessionId != null && getCurrentTimeMillis() >= nextSessionTimeout) {
            // Expired session → reset
            sessionId = null
        }

        if (sessionId != null) {
            presenter.trackCustomEvent("session_continue", emptyMap())
        } else {
            sessionId = Uuid.random().toString()
            ExperimentManager.setSessionId(sessionId!!)
            presenter.trackCustomEvent("session_start", emptyMap())
        }
    }

    private fun startHeartbeatLoop() {
        heartbeatJob = launch {
            while (isActive && !isDisposed) {
                if (!isPaused && getCurrentTimeMillis() >= nextHeartbeat) {
                    presenter.trackCustomEvent("session_heartbeat", emptyMap())
                    nextHeartbeat = getCurrentTimeMillis() + config.heartbeatPeriodMs
                }
                delay(100)
            }
        }
    }

    fun dispose() {
        if (isDisposed) return
        isDisposed = true
        presenter.trackCustomEvent("session_end", emptyMap())
        job.cancel()
        heartbeatJob?.cancel()
    }
}
