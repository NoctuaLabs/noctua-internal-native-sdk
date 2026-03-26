package com.noctuagames.labs.sdk.integration

import com.noctuagames.labs.sdk.data.database.entity.EventEntity
import com.noctuagames.labs.sdk.fakes.FakeEventDao
import com.noctuagames.labs.sdk.fakes.FakeExternalEventDao
import com.noctuagames.labs.sdk.fakes.FakeRemoteNoctuaInternal
import com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity
import com.noctuagames.labs.sdk.utils.Result
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Integration tests verifying the full event pipeline:
 * store locally → flush to remote → clear local on success.
 *
 * These tests exercise the fake DAO + fake remote together to validate
 * the pipeline logic that the presenter orchestrates.
 */
class EventPipelineIntegrationTest {

    private lateinit var eventDao: FakeEventDao
    private lateinit var externalEventDao: FakeExternalEventDao
    private lateinit var remote: FakeRemoteNoctuaInternal

    @BeforeTest
    fun setup() {
        eventDao = FakeEventDao()
        externalEventDao = FakeExternalEventDao()
        remote = FakeRemoteNoctuaInternal()
    }

    @Test
    fun storeLocallyThenFlush_success_clearsLocalEvents() = runTest {
        // 1. Store events locally (simulating offline tracking)
        eventDao.insert(EventEntity(events = """{"event_name":"test_1"}"""))
        eventDao.insert(EventEntity(events = """{"event_name":"test_2"}"""))
        eventDao.insert(EventEntity(events = """{"event_name":"test_3"}"""))

        assertEquals(3, eventDao.getAll().size)

        // 2. Flush to remote (simulating network becoming available)
        remote.shouldSucceed = true
        val localEvents = eventDao.getAll()
        val payloads = localEvents.map { it.events }
        val result = remote.sendEvents(payloads)

        // 3. On success, clear local storage
        assertTrue(result is Result.Success)
        eventDao.deleteAll()

        // 4. Verify
        assertEquals(0, eventDao.getAll().size)
        assertEquals(1, remote.capturedEvents.size)
        assertEquals(3, remote.capturedEvents[0].size)
    }

    @Test
    fun storeLocallyThenFlush_failure_retainsLocalEvents() = runTest {
        // 1. Store events locally
        eventDao.insert(EventEntity(events = """{"event_name":"important"}"""))

        // 2. Attempt flush — remote fails
        remote.shouldSucceed = false
        val localEvents = eventDao.getAll()
        val result = remote.sendEvents(localEvents.map { it.events })

        // 3. On failure, do NOT clear local storage
        assertTrue(result is Result.Error)
        // Events remain for retry
        assertEquals(1, eventDao.getAll().size)
    }

    @Test
    fun externalEventBatchWorkflow_insertQueryDelete() = runTest {
        // 1. Insert external events
        val id1 = externalEventDao.insertSingle(ExternalEventEntity(eventJson = """{"purchase":"item_a"}"""))
        val id2 = externalEventDao.insertSingle(ExternalEventEntity(eventJson = """{"purchase":"item_b"}"""))
        val id3 = externalEventDao.insertSingle(ExternalEventEntity(eventJson = """{"purchase":"item_c"}"""))

        assertEquals(3, externalEventDao.getCount())

        // 2. Query first batch
        val batch1 = externalEventDao.getBatch(limit = 2, offset = 0)
        assertEquals(2, batch1.size)

        // 3. Delete processed events
        val deletedCount = externalEventDao.deleteByIds(listOf(id1, id2))
        assertEquals(2, deletedCount)

        // 4. Query remaining
        val remaining = externalEventDao.getBatch(limit = 10, offset = 0)
        assertEquals(1, remaining.size)
        assertTrue(remaining[0].eventJson.contains("item_c"))
    }
}
