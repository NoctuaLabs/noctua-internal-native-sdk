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

    // ---- noctua_user_engagement (Unity parity, gated by nativeInternalTrackerEnabled) ----

    @Test
    fun engagement_disabledByDefault_noEngagementEvents() = runTest {
        val tracker = SessionTracker(
            config = SessionTrackerConfig(heartbeatPeriodMs = 60_000, sessionTimeoutMs = 300_000),
            eventSink = eventSink,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        tracker.onApplicationPause(false) // start
        tracker.onApplicationPause(true)  // pause
        tracker.dispose()                 // end

        assertEquals(0, eventSink.countOf("noctua_user_engagement"))

        tracker.dispose()
    }

    @Test
    fun engagement_firesBeforeEachSessionPartner_whenEnabled() = runTest {
        val tracker = SessionTracker(
            config = SessionTrackerConfig(heartbeatPeriodMs = 60_000, sessionTimeoutMs = 300_000),
            eventSink = eventSink,
            userEngagementEnabled = true,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        tracker.onApplicationPause(false) // start
        tracker.onApplicationPause(true)  // pause

        // Each engagement event immediately precedes its session partner.
        val names = eventSink.eventNames()
        assertEquals(
            listOf(
                "noctua_user_engagement", "session_start",
                "noctua_user_engagement", "session_pause"
            ),
            names
        )

        tracker.dispose()
    }

    @Test
    fun engagement_start_reportsZeroAndStartLifecycle() = runTest {
        val tracker = SessionTracker(
            config = SessionTrackerConfig(),
            eventSink = eventSink,
            userEngagementEnabled = true,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        tracker.onApplicationPause(false) // start

        val engagement = eventSink.trackedEvents.first { it.eventName == "noctua_user_engagement" }
        assertEquals("start", engagement.properties["lifecycle"])
        assertEquals(0L, engagement.properties["engagement_time_msec"])

        tracker.dispose()
    }

    @Test
    fun engagement_pause_reportsForegroundElapsedTime() = runTest {
        val tracker = SessionTracker(
            config = SessionTrackerConfig(heartbeatPeriodMs = 60_000, sessionTimeoutMs = 300_000),
            eventSink = eventSink,
            userEngagementEnabled = true,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        tracker.onApplicationPause(false) // start (mark = 0)
        virtualTimeMs += 8_000            // 8s in foreground
        tracker.onApplicationPause(true)  // pause

        val pauseEngagement = eventSink.trackedEvents
            .last { it.eventName == "noctua_user_engagement" }
        assertEquals("pause", pauseEngagement.properties["lifecycle"])
        assertEquals(8_000L, pauseEngagement.properties["engagement_time_msec"])

        tracker.dispose()
    }

    @Test
    fun engagement_resumeAfterBackground_reportsZeroNotBackgroundTime() = runTest {
        val tracker = SessionTracker(
            config = SessionTrackerConfig(heartbeatPeriodMs = 60_000, sessionTimeoutMs = 300_000),
            eventSink = eventSink,
            userEngagementEnabled = true,
            coroutineContext = StandardTestDispatcher(testScheduler),
            timeProvider = timeProvider
        )

        tracker.onApplicationPause(false) // start
        tracker.onApplicationPause(true)  // pause
        virtualTimeMs += 30_000           // 30s in background
        tracker.onApplicationPause(false) // resume (still within 5min timeout)

        val resumeEngagement = eventSink.trackedEvents
            .last { it.eventName == "noctua_user_engagement" }
        assertEquals("foreground", resumeEngagement.properties["lifecycle"])
        // Background time must NOT count toward engagement.
        assertEquals(0L, resumeEngagement.properties["engagement_time_msec"])
        assertTrue(eventSink.hasEvent("session_continue"))

        tracker.dispose()
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
