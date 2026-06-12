package com.noctuagames.labs.sdk.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.noctuagames.labs.sdk.data.database.entity.EventEntity

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventEntity)

    @Query("SELECT * FROM events")
    suspend fun getAll(): List<EventEntity>

    @Query("SELECT * FROM events ORDER BY id ASC LIMIT :limit")
    suspend fun getBatch(limit: Int): List<EventEntity>

    @Query("DELETE FROM events WHERE id IN (:ids)")
    suspend fun deleteByIds(ids: List<Long>): Int

    @Query("SELECT COUNT(*) FROM events")
    suspend fun getCount(): Int

    // Oldest rows are evicted first; events are append-only so id order == insertion order.
    @Query("DELETE FROM events WHERE id NOT IN (SELECT id FROM events ORDER BY id DESC LIMIT :keep)")
    suspend fun trimToSize(keep: Int): Int

    @Query("DELETE FROM events")
    suspend fun deleteAll()
}