package com.noctuagames.labs.sdk.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity

@Dao
interface ExternalEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: ExternalEventEntity)

    @Query("SELECT * FROM external_events")
    suspend fun getAll(): List<ExternalEventEntity>


    @Query("DELETE FROM external_events")
    suspend fun deleteAll()
}