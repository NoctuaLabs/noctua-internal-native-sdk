package com.noctuagames.labs.sdk

import com.noctuagames.labs.sdk.fakes.FakeEventDao
import com.noctuagames.labs.sdk.fakes.FakeExternalEventDao
import com.noctuagames.labs.sdk.fakes.FakeRemoteNoctuaInternal
import com.noctuagames.labs.sdk.utils.ExperimentManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests for external event storage and experiment/tag delegation.
 *
 * NOTE: Full presenter tests (trackCustomEvent, flushLocalEvents) require
 * platform-specific DeviceUtils and NetworkStatusProvider. Those are tested
 * in the desktop-specific test source set or via integration tests.
 * These commonTest tests cover the parts that don't touch those dependencies.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class NoctuaAnalyticsPresenterTest {

    private lateinit var fakeEventDao: FakeEventDao
    private lateinit var fakeExternalEventDao: FakeExternalEventDao
    private lateinit var fakeRemote: FakeRemoteNoctuaInternal

    @BeforeTest
    fun setup() {
        fakeEventDao = FakeEventDao()
        fakeExternalEventDao = FakeExternalEventDao()
        fakeRemote = FakeRemoteNoctuaInternal()
        ExperimentManager.clear()
    }

    @AfterTest
    fun teardown() {
        ExperimentManager.clear()
    }

    // --- External Event DAO delegation tests ---

    @Test
    fun insertExternalEvent_delegatesToDao() = runTest {
        val eventJson = """{"type":"purchase","amount":9.99}"""
        fakeExternalEventDao.insertSingle(
            com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity(eventJson = eventJson)
        )

        assertEquals(1, fakeExternalEventDao.getCount())
    }

    @Test
    fun getExternalEventsBatch_returnsPaginatedResults() = runTest {
        repeat(5) { i ->
            fakeExternalEventDao.insertSingle(
                com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity(eventJson = """{"idx":$i}""")
            )
        }

        val batch = fakeExternalEventDao.getBatch(limit = 2, offset = 1)

        assertEquals(2, batch.size)
        assertTrue(batch[0].eventJson.contains("1"))
        assertTrue(batch[1].eventJson.contains("2"))
    }

    @Test
    fun deleteExternalEventsByIds_removesCorrectEvents() = runTest {
        val id1 = fakeExternalEventDao.insertSingle(
            com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity(eventJson = """{"a":1}""")
        )
        val id2 = fakeExternalEventDao.insertSingle(
            com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity(eventJson = """{"b":2}""")
        )
        fakeExternalEventDao.insertSingle(
            com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity(eventJson = """{"c":3}""")
        )

        val deleted = fakeExternalEventDao.deleteByIds(listOf(id1, id2))

        assertEquals(2, deleted)
        assertEquals(1, fakeExternalEventDao.getCount())
    }

    @Test
    fun getExternalEventCount_returnsCorrectCount() = runTest {
        repeat(3) {
            fakeExternalEventDao.insertSingle(
                com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity(eventJson = "{}")
            )
        }

        assertEquals(3, fakeExternalEventDao.getCount())
    }

    @Test
    fun saveExternalEvents_insertsViaLegacyMethod() = runTest {
        fakeExternalEventDao.insert(
            com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity(eventJson = """[{"event":"test"}]""")
        )

        val all = fakeExternalEventDao.getAll()
        assertEquals(1, all.size)
    }

    @Test
    fun deleteExternalEvents_clearsAll() = runTest {
        repeat(5) {
            fakeExternalEventDao.insertSingle(
                com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity(eventJson = "{}")
            )
        }

        fakeExternalEventDao.deleteAll()

        assertEquals(0, fakeExternalEventDao.getCount())
    }

    // --- ExperimentManager delegation tests ---

    @Test
    fun setSessionTag_getSessionTag_delegatesToExperimentManager() {
        ExperimentManager.setSessionTag("my-feature")
        assertEquals("my-feature", ExperimentManager.getSessionTag())
    }

    @Test
    fun setExperiment_getExperiment_delegatesToExperimentManager() {
        ExperimentManager.setExperiment("experiment-v2")
        assertEquals("experiment-v2", ExperimentManager.getExperiment())
    }

    @Test
    fun setGeneralExperiment_getGeneralExperiment_delegatesToExperimentManager() {
        ExperimentManager.setGeneralExperiment("flagKey", "flagValue")
        assertEquals("flagValue", ExperimentManager.getGeneralExperiment("flagKey"))
    }

    // --- EventDao tests ---

    @Test
    fun eventDao_insertAndGetAll() = runTest {
        fakeEventDao.insert(
            com.noctuagames.labs.sdk.data.database.entity.EventEntity(events = """{"event":"test"}""")
        )

        val all = fakeEventDao.getAll()
        assertEquals(1, all.size)
        assertTrue(all[0].events.contains("test"))
    }

    @Test
    fun eventDao_deleteAll_clearsEvents() = runTest {
        repeat(3) {
            fakeEventDao.insert(
                com.noctuagames.labs.sdk.data.database.entity.EventEntity(events = "{}")
            )
        }

        fakeEventDao.deleteAll()

        assertEquals(0, fakeEventDao.getAll().size)
    }

    // --- Remote tests ---

    @Test
    fun fakeRemote_sendEvents_capturesPayload_onSuccess() = runTest {
        fakeRemote.shouldSucceed = true
        val events = listOf("""{"event":"test"}""")

        val result = fakeRemote.sendEvents(events)

        assertTrue(result is com.noctuagames.labs.sdk.utils.Result.Success)
        assertEquals(1, fakeRemote.capturedEvents.size)
        assertEquals(events, fakeRemote.capturedEvents[0])
    }

    @Test
    fun fakeRemote_sendEvents_returnsError_onFailure() = runTest {
        fakeRemote.shouldSucceed = false
        fakeRemote.errorToReturn = com.noctuagames.labs.sdk.utils.DataError.Remote.NO_INTERNET

        val result = fakeRemote.sendEvents(listOf("{}"))

        assertTrue(result is com.noctuagames.labs.sdk.utils.Result.Error)
        assertEquals(
            com.noctuagames.labs.sdk.utils.DataError.Remote.NO_INTERNET,
            (result as com.noctuagames.labs.sdk.utils.Result.Error).error
        )
    }
}
