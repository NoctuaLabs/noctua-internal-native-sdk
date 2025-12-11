package com.noctuagames.labs.sdk.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.noctuagames.labs.sdk.data.database.dao.EventDao
import com.noctuagames.labs.sdk.data.database.entity.EventEntity
import com.noctuagames.labs.sdk.data.local.dao.ExternalEventDao
import com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity

@Database(
    entities = [EventEntity::class, ExternalEventEntity::class],
    version = 1
)
@TypeConverters(StringListTypeConverter::class)
@ConstructedBy(NoctuaDatabaseConstructor::class)
abstract class NoctuaDatabase: RoomDatabase() {
    abstract val eventDao: EventDao
    abstract val externalEventDao: ExternalEventDao

    companion object {
        const val DB_NAME = "noctua.db"
    }
}