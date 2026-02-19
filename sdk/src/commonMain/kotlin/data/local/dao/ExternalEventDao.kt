package com.noctuagames.labs.sdk.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity

@Dao
interface ExternalEventDao {

    // NEW: Per-row operations for unlimited event storage
    @Insert
    suspend fun insertSingle(event: ExternalEventEntity): Long

    @Query("SELECT * FROM external_events ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getBatch(limit: Int, offset: Int): List<ExternalEventEntity>

    @Query("DELETE FROM external_events WHERE id IN (:ids)")
    suspend fun deleteByIds(ids: List<Long>): Int

    @Query("SELECT COUNT(*) FROM external_events")
    suspend fun getCount(): Int

    // KEEP: Existing methods for backward compatibility during migration
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: ExternalEventEntity)

    @Query("SELECT * FROM external_events")
    suspend fun getAll(): List<ExternalEventEntity>

    @Query("DELETE FROM external_events")
    suspend fun deleteAll()
}
