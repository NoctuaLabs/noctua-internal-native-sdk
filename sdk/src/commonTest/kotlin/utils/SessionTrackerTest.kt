package com.noctuagames.labs.sdk.utils

import com.noctuagames.labs.sdk.fakes.FakeSessionEventSink
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class SessionTrackerTest {

    private lateinit var eventSink: FakeSessionEventSink

    /** Mutable virtual clock that tests can advance manually. */
    private var virtualTimeMs = 0L
    private val timeProvider: () -> Long = { virtualTimeMs }

    @BeforeTest
    fun setup() {
        eventSink = FakeSessionEventSink()
        virtualTimeMs = 0L
        ExperimentManager.clear()
    }

    @AfterTest
    fun teardown() {
        ExperimentManager.clear()
    }

    @Test
    fun onApplicationPause_false_emitsSessionStart() = runTest {
        val tracker = SessionTracker(
            config = SessionTrackerConfig(heartbeatPeriodMs = 60_000, sessionTimeoutMs = 300_000),
            eventSink = eventSink,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        tracker.onApplicationPause(false)

        assertTrue(eventSink.hasEvent("session_start"))
        assertEquals(1, eventSink.countOf("session_start"))

        tracker.dispose()
    }

    @Test
    fun onApplicationPause_true_afterStart_emitsSessionPause() = runTest {
        val tracker = SessionTracker(
            config = SessionTrackerConfig(heartbeatPeriodMs = 60_000, sessionTimeoutMs = 300_000),
            eventSink = eventSink,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        tracker.onApplicationPause(false) // start
        tracker.onApplicationPause(true)  // pause

        assertTrue(eventSink.hasEvent("session_start"))
        assertTrue(eventSink.hasEvent("session_pause"))

        tracker.dispose()
    }

    @Test
    fun onApplicationPause_resumeBeforeTimeout_emitsSessionContinue() = runTest {
        val timeoutMs = 5_000L
        val tracker = SessionTracker(
            config = SessionTrackerConfig(heartbeatPeriodMs = 60_000, sessionTimeoutMs = timeoutMs),
            eventSink = eventSink,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        tracker.onApplicationPause(false) // start
        tracker.onApplicationPause(true)  // pause

        // Resume before timeout (5s)
        virtualTimeMs += 2_000
        tracker.onApplicationPause(false) // resume

        assertTrue(eventSink.hasEvent("session_continue"))
        assertEquals(1, eventSink.countOf("session_start"))

        tracker.dispose()
    }

    @Test
    fun onApplicationPause_resumeAfterTimeout_emitsNewSessionStart() = runTest {
        val timeoutMs = 1_000L
        val tracker = SessionTracker(
            config = SessionTrackerConfig(heartbeatPeriodMs = 60_000, sessionTimeoutMs = timeoutMs),
            eventSink = eventSink,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        tracker.onApplicationPause(false) // start (session_start #1)
        val firstSessionId = ExperimentManager.getSessionId()

        tracker.onApplicationPause(true)  // pause

        // Wait past timeout
        virtualTimeMs += timeoutMs + 500
        tracker.onApplicationPause(false) // resume — should be new session

        assertEquals(2, eventSink.countOf("session_start"))
        val secondSessionId = ExperimentManager.getSessionId()
        assertNotEquals(firstSessionId, secondSessionId)

        tracker.dispose()
    }

    @Test
    fun heartbeat_firesAfterPeriod_whenNotPaused() = runTest {
        val heartbeatMs = 500L
        val tracker = SessionTracker(
            config = SessionTrackerConfig(heartbeatPeriodMs = heartbeatMs, sessionTimeoutMs = 300_000),
            eventSink = eventSink,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        tracker.onApplicationPause(false) // start session

        // Advance virtual clock past heartbeat period
        virtualTimeMs += heartbeatMs + 100
        // Advance coroutine dispatcher to run the heartbeat loop iterations
        advanceTimeBy(heartbeatMs + 200)

        assertTrue(eventSink.hasEvent("session_heartbeat"), "Expected session_heartbeat event")

        tracker.dispose()
    }

    @Test
    fun heartbeat_doesNotFire_whenPaused() = runTest {
        val heartbeatMs = 500L
        val tracker = SessionTracker(
            config = SessionTrackerConfig(heartbeatPeriodMs = heartbeatMs, sessionTimeoutMs = 300_000),
            eventSink = eventSink,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        // Don't resume — isPaused stays true
        virtualTimeMs += heartbeatMs + 200
        advanceTimeBy(heartbeatMs + 200)

        assertEquals(0, eventSink.countOf("session_heartbeat"))

        tracker.dispose()
    }

    @Test
    fun dispose_emitsSessionEnd_andStopsHeartbeat() = runTest {
        val heartbeatMs = 500L
        val tracker = SessionTracker(
            config = SessionTrackerConfig(heartbeatPeriodMs = heartbeatMs, sessionTimeoutMs = 300_000),
            eventSink = eventSink,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        tracker.onApplicationPause(false) // start
        tracker.dispose()

        assertTrue(eventSink.hasEvent("session_end"))

        // Advance time — no more heartbeats should fire after dispose
        val heartbeatCountAfterDispose = eventSink.countOf("session_heartbeat")
        virtualTimeMs += heartbeatMs * 3
        advanceTimeBy(heartbeatMs * 3)
        assertEquals(heartbeatCountAfterDispose, eventSink.countOf("session_heartbeat"))
    }

    @Test
    fun duplicatePause_isIgnored() = runTest {
        val tracker = SessionTracker(
            config = SessionTrackerConfig(),
            eventSink = eventSink,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        tracker.onApplicationPause(false) // start
        tracker.onApplicationPause(false) // duplicate — should be ignored

        assertEquals(1, eventSink.countOf("session_start"))

        tracker.dispose()
    }
}
