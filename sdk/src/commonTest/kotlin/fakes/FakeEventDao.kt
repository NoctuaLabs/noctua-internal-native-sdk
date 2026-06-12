package com.noctuagames.labs.sdk.fakes

import com.noctuagames.labs.sdk.data.database.entity.EventEntity
import com.noctuagames.labs.sdk.data.database.dao.EventDao

internal class FakeEventDao : EventDao {
    private val events = mutableListOf<EventEntity>()
    private var nextId = 1L

    override suspend fun insert(event: EventEntity) {
        events.add(event.copy(id = nextId++))
    }

    override suspend fun getAll(): List<EventEntity> {
        return events.toList()
    }

    override suspend fun getBatch(limit: Int): List<EventEntity> {
        return events.sortedBy { it.id }.take(limit)
    }

    override suspend fun deleteByIds(ids: List<Long>): Int {
        val before = events.size
        events.removeAll { it.id in ids }
        return before - events.size
    }

    override suspend fun getCount(): Int = events.size

    override suspend fun trimToSize(keep: Int): Int {
        val keepIds = events.sortedByDescending { it.id }.take(keep).map { it.id }.toSet()
        val before = events.size
        events.removeAll { it.id !in keepIds }
        return before - events.size
    }

    override suspend fun deleteAll() {
        events.clear()
    }

    fun reset() {
        events.clear()
        nextId = 1L
    }

    fun count(): Int = events.size
}
