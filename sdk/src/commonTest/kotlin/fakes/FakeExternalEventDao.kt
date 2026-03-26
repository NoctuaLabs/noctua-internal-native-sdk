package com.noctuagames.labs.sdk.fakes

import com.noctuagames.labs.sdk.data.local.dao.ExternalEventDao
import com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity

internal class FakeExternalEventDao : ExternalEventDao {
    private val events = mutableListOf<ExternalEventEntity>()
    private var nextId = 1L

    override suspend fun insertSingle(event: ExternalEventEntity): Long {
        val id = nextId++
        events.add(event.copy(id = id))
        return id
    }

    override suspend fun getBatch(limit: Int, offset: Int): List<ExternalEventEntity> {
        return events.drop(offset).take(limit)
    }

    override suspend fun deleteByIds(ids: List<Long>): Int {
        val before = events.size
        events.removeAll { it.id in ids }
        return before - events.size
    }

    override suspend fun getCount(): Int = events.size

    override suspend fun insert(event: ExternalEventEntity) {
        events.add(event.copy(id = nextId++))
    }

    override suspend fun getAll(): List<ExternalEventEntity> {
        return events.toList()
    }

    override suspend fun deleteAll() {
        events.clear()
    }

    fun reset() {
        events.clear()
        nextId = 1L
    }
}
