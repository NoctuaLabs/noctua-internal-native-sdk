package com.noctuagames.labs.sdk.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.noctuagames.labs.sdk.data.database.dao.EventDao
import com.noctuagames.labs.sdk.data.database.entity.EventEntity

@Database(
    entities = [EventEntity::class],
    version = 1
)
@TypeConverters(StringListTypeConverter::class)
@ConstructedBy(NoctuaDatabaseConstructor::class)
abstract class NoctuaDatabase: RoomDatabase() {
    abstract val eventDao: EventDao

    companion object {
        const val DB_NAME = "noctua.db"
    }
}