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


    @Query("DELETE FROM events")
    suspend fun deleteAll()
}