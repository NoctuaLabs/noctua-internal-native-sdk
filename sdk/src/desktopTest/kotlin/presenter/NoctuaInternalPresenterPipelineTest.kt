package com.noctuagames.labs.sdk.presenter

import com.noctuagames.labs.sdk.data.database.entity.EventEntity
import com.noctuagames.labs.sdk.data.local.dao.ExternalEventDao
import com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity
import com.noctuagames.labs.sdk.data.models.EventData
import com.noctuagames.labs.sdk.data.models.EventResponse
import com.noctuagames.labs.sdk.data.models.NoctuaConfig
import com.noctuagames.labs.sdk.data.models.NoctuaFeatureConfig
import com.noctuagames.labs.sdk.data.remote.RemoteNoctuaInternal
import com.noctuagames.labs.sdk.fakes.FakeEventDao
import com.noctuagames.labs.sdk.fakes.FakeExternalEventDao
import com.noctuagames.labs.sdk.fakes.FakeRemoteNoctuaInternal
import com.noctuagames.labs.sdk.utils.DataError
import com.noctuagames.labs.sdk.utils.DeviceUtils
import com.noctuagames.labs.sdk.utils.ExperimentManager
import com.noctuagames.labs.sdk.utils.Result
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Tests that drive the real [NoctuaInternalPresenter] delivery pipeline —
 * not re-implementations against fakes. These cover the flush/delete
 * correctness guarantees (no loss of events inserted mid-upload, retention
 * on failure, bounded batches) and the error-safety of consumer callbacks.
 */
@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class NoctuaInternalPresenterPipelineTest {

    private lateinit var eventDao: FakeEventDao
    private lateinit var externalEventDao: FakeExternalEventDao
    private lateinit var remote: FakeRemoteNoctuaInternal

    // Tracker enabled: these tests exercise the session/flush/upload pipeline,
    // which only runs when nativeInternalTrackerEnabled == true.
    private val config = NoctuaConfig(
        noctua = NoctuaFeatureConfig(nativeInternalTrackerEnabled = true)
    )

    @BeforeTest
    fun setup() {
        eventDao = FakeEventDao()
        externalEventDao = FakeExternalEventDao()
        remote = FakeRemoteNoctuaInternal()
        ExperimentManager.clear()
    }

    private fun buildPresenter(
        dispatcher: kotlinx.coroutines.CoroutineDispatcher,
        remoteImpl: RemoteNoctuaInternal = remote,
        connected: Boolean = true
    ) = NoctuaInternalPresenter(
        deviceUtils = DeviceUtils(),
        noctuaConfig = config,
        remote = remoteImpl,
        eventDao = eventDao,
        externalEventDao = externalEventDao,
        dispatcher = dispatcher,
        isConnected = { connected }
    )

    @Test
    fun flush_deletesOnlySentEvents_eventInsertedDuringUploadSurvives() = runTest {
        val gate = CompletableDeferred<Unit>()
        val gatedRemote = object : RemoteNoctuaInternal {
            val captured = mutableListOf<List<String>>()
            override suspend fun sendEvents(events: List<String>): Result<EventResponse, DataError.Remote> {
                captured.add(events.toList())
                gate.await() // hold the upload in flight
                return Result.Success(EventResponse(success = true, data = EventData(message = "ok")))
            }
            override fun close() {}
        }

        eventDao.insert(EventEntity(events = """{"event_name":"pre_flush"}"""))

        val presenter = buildPresenter(StandardTestDispatcher(testScheduler), remoteImpl = gatedRemote)
        advanceUntilIdle() // init flush is now suspended inside sendEvents

        assertTrue(gatedRemote.captured.isNotEmpty(), "flush should have started an upload")

        // An event arrives while the upload is in flight
        eventDao.insert(EventEntity(events = """{"event_name":"during_upload"}"""))

        gate.complete(Unit)
        advanceUntilIdle()

        // The in-flight batch must not take the new event down with it:
        // the old deleteAll() behavior would have erased it unsent.
        val survivors = eventDao.getAll().map { it.events }
        assertTrue(
            survivors.any { it.contains("during_upload") } ||
                gatedRemote.captured.flatten().any { it.contains("during_upload") },
            "event inserted during upload must be either still stored or sent — never silently dropped"
        )

        presenter.onInternalNoctuaDispose()
    }

    @Test
    fun flush_onServerError_retainsAllEvents() = runTest {
        remote.shouldSucceed = false
        remote.errorToReturn = DataError.Remote.SERVER

        val presenter = buildPresenter(StandardTestDispatcher(testScheduler))
        advanceUntilIdle()

        presenter.trackCustomEvent("custom_event", emptyMap())
        advanceUntilIdle()

        val before = eventDao.count()
        presenter.flushLocalEvents()
        advanceUntilIdle()

        assertEquals(before, eventDao.count(), "no events may be deleted after a failed delivery")

        presenter.onInternalNoctuaDispose()
    }

    @Test
    fun flush_sendsInBoundedBatches_andDrainsBacklog() = runTest {
        repeat(250) { i ->
            eventDao.insert(EventEntity(events = """{"event_name":"bulk_$i"}"""))
        }

        val presenter = buildPresenter(StandardTestDispatcher(testScheduler))
        advanceUntilIdle()

        assertEquals(0, eventDao.count(), "backlog must fully drain on success")
        assertTrue(remote.capturedEvents.size >= 3, "250 events must be split across multiple batches")
        assertTrue(
            remote.capturedEvents.all { it.size <= 100 },
            "no single payload may exceed the batch size, got sizes ${remote.capturedEvents.map { it.size }}"
        )

        presenter.onInternalNoctuaDispose()
    }

    @Test
    fun getExternalEventsBatch_producesValidJson_evenWithQuotesInStoredValue() = runTest {
        val presenter = buildPresenter(StandardTestDispatcher(testScheduler))
        advanceUntilIdle()

        presenter.insertExternalEvent("""{"name":"valid","value":1}""")
        presenter.insertExternalEvent("""not-json with "quotes" and \ backslash""")
        advanceUntilIdle()

        var payload: String? = null
        presenter.getExternalEventsBatch(limit = 10, offset = 0) { payload = it }
        advanceUntilIdle()

        assertNotNull(payload)
        val parsed = Json.parseToJsonElement(payload!!).jsonArray
        assertEquals(2, parsed.size)
        // Valid JSON is embedded as an element; broken input survives as an escaped string
        assertEquals("valid", parsed[0].jsonObject["eventJson"]!!.jsonObject["name"]!!.jsonPrimitive.content)
        assertEquals(
            """not-json with "quotes" and \ backslash""",
            parsed[1].jsonObject["eventJson"]!!.jsonPrimitive.content
        )

        presenter.onInternalNoctuaDispose()
    }

    @Test
    fun deleteExternalEventsByIds_skipsMalformedTokens_andStillFiresCallback() = runTest {
        val presenter = buildPresenter(StandardTestDispatcher(testScheduler))
        advanceUntilIdle()

        presenter.insertExternalEvent("""{"e":1}""")
        presenter.insertExternalEvent("""{"e":2}""")
        presenter.insertExternalEvent("""{"e":3}""")
        advanceUntilIdle()

        var deleted = -1
        presenter.deleteExternalEventsByIds("[1, abc, 3]") { deleted = it }
        advanceUntilIdle()

        assertEquals(2, deleted, "valid ids must be deleted, malformed tokens skipped, callback must fire")

        presenter.onInternalNoctuaDispose()
    }

    @Test
    fun legacyBulkExternalEvents_saveGetDelete_roundTrips() = runTest {
        val presenter = buildPresenter(StandardTestDispatcher(testScheduler))
        advanceUntilIdle()

        presenter.saveExternalEvents("""{"e":"a"}""")
        presenter.saveExternalEvents("""{"e":"b"}""")
        advanceUntilIdle()

        var fetched: List<String>? = null
        presenter.getExternalEvents { fetched = it }
        advanceUntilIdle()
        assertEquals(listOf("""{"e":"a"}""", """{"e":"b"}"""), fetched)

        presenter.deleteExternalEvents()
        advanceUntilIdle()

        var afterDelete: List<String>? = null
        presenter.getExternalEvents { afterDelete = it }
        advanceUntilIdle()
        assertEquals(emptyList(), afterDelete)

        presenter.onInternalNoctuaDispose()
    }

    @Test
    fun dispose_closesTheRemoteTransport() = runTest {
        val presenter = buildPresenter(StandardTestDispatcher(testScheduler))
        advanceUntilIdle()

        assertTrue(!remote.closed)
        presenter.onInternalNoctuaDispose()
        advanceUntilIdle()

        assertTrue(remote.closed, "HTTP client must be closed on dispose")
    }

    @Test
    fun trackerEnabled_tracksCustomEvent_storesThenUploadsOnFlush() = runTest {
        // Shared `config` has nativeInternalTrackerEnabled == true.
        val presenter = buildPresenter(StandardTestDispatcher(testScheduler))
        advanceUntilIdle()

        presenter.trackCustomEvent("custom_event", emptyMap())
        advanceUntilIdle()
        assertEquals(1, eventDao.count(), "tracker enabled: custom event must be stored")

        presenter.flushLocalEvents()
        advanceUntilIdle()

        assertTrue(
            remote.capturedEvents.flatten().any { it.contains("custom_event") },
            "tracker enabled: stored event must be uploaded on flush"
        )
        assertEquals(0, eventDao.count(), "tracker enabled: delivered events are deleted")

        presenter.onInternalNoctuaDispose()
    }

    @Test
    fun trackerDisabled_whenFlagAbsent_behavesAsDisabled() = runTest {
        // noctua == null → nativeInternalTrackerEnabled resolves to false.
        val absentConfig = NoctuaConfig(null, null, null, null, null, null)
        eventDao.insert(EventEntity(events = """{"event_name":"preexisting"}"""))

        val presenter = NoctuaInternalPresenter(
            deviceUtils = DeviceUtils(),
            noctuaConfig = absentConfig,
            remote = remote,
            eventDao = eventDao,
            externalEventDao = externalEventDao,
            dispatcher = StandardTestDispatcher(testScheduler),
            isConnected = { true }
        )
        advanceUntilIdle()

        presenter.trackCustomEvent("custom_event", emptyMap())
        advanceUntilIdle()

        assertEquals(1, eventDao.count(), "absent flag must behave as disabled: nothing tracked or flushed")
        assertTrue(remote.capturedEvents.isEmpty(), "absent flag must behave as disabled: nothing sent")

        presenter.onInternalNoctuaDispose()
    }

    @Test
    fun trackerDisabled_skipsTracking_flushAndUpload_butKeepsExternalStore() = runTest {
        val disabledConfig = NoctuaConfig(
            noctua = NoctuaFeatureConfig(nativeInternalTrackerEnabled = false)
        )
        // Seed a pre-existing event; init must NOT flush it when the tracker is off.
        eventDao.insert(EventEntity(events = """{"event_name":"preexisting"}"""))

        val presenter = NoctuaInternalPresenter(
            deviceUtils = DeviceUtils(),
            noctuaConfig = disabledConfig,
            remote = remote,
            eventDao = eventDao,
            externalEventDao = externalEventDao,
            dispatcher = StandardTestDispatcher(testScheduler),
            isConnected = { true }
        )
        advanceUntilIdle()

        assertEquals(1, eventDao.count(), "tracker disabled: init must not upload existing events")
        assertTrue(remote.capturedEvents.isEmpty(), "tracker disabled: nothing may be sent on init")

        // Custom event tracking is a no-op (neither stored nor uploaded).
        presenter.trackCustomEvent("custom_event", emptyMap())
        presenter.trackCustomEventWithRevenue("purchase", emptyMap(), 9.99, "USD")
        advanceUntilIdle()
        assertEquals(1, eventDao.count(), "tracker disabled: custom events must not be stored")
        assertTrue(remote.capturedEvents.isEmpty(), "tracker disabled: custom events must not be sent")

        // An explicit flush is also a no-op.
        presenter.flushLocalEvents()
        advanceUntilIdle()
        assertEquals(1, eventDao.count(), "tracker disabled: explicit flush must not upload")
        assertTrue(remote.capturedEvents.isEmpty())

        // The external event store is independent of the tracker gate.
        presenter.insertExternalEvent("""{"e":1}""")
        advanceUntilIdle()
        var count = -1
        presenter.getExternalEventCount { count = it }
        advanceUntilIdle()
        assertEquals(1, count, "tracker disabled: external event store must still work")

        // Dispose must still release the transport even though sessionTracker is null.
        presenter.onInternalNoctuaDispose()
        advanceUntilIdle()
        assertTrue(remote.closed, "dispose must close the transport even when the tracker is disabled")
    }

    @Test
    fun externalCallbacks_fireWithSafeDefaults_whenDaoThrows() = runTest {
        val throwingDao = object : ExternalEventDao by externalEventDao {
            override suspend fun getCount(): Int = throw IllegalStateException("disk full")
            override suspend fun getBatch(limit: Int, offset: Int): List<ExternalEventEntity> =
                throw IllegalStateException("disk full")
        }
        val presenter = NoctuaInternalPresenter(
            deviceUtils = DeviceUtils(),
            noctuaConfig = config,
            remote = remote,
            eventDao = eventDao,
            externalEventDao = throwingDao,
            dispatcher = StandardTestDispatcher(testScheduler),
            isConnected = { true }
        )
        advanceUntilIdle()

        var count = -1
        presenter.getExternalEventCount { count = it }
        var batch: String? = null
        presenter.getExternalEventsBatch(10, 0) { batch = it }
        advanceUntilIdle()

        assertEquals(0, count, "callback must fire with a safe default instead of hanging forever")
        assertEquals("[]", batch, "callback must fire with a safe default instead of hanging forever")

        presenter.onInternalNoctuaDispose()
    }
}
