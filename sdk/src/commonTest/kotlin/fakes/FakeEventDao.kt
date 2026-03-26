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

    override suspend fun deleteAll() {
        events.clear()
    }

    fun reset() {
        events.clear()
        nextId = 1L
    }

    fun count(): Int = events.size
}
