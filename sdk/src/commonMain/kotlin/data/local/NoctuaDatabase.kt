package com.noctuagames.labs.sdk.data.database

import androidx.room.AutoMigration
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.noctuagames.labs.sdk.data.database.dao.EventDao
import com.noctuagames.labs.sdk.data.database.entity.EventEntity
import com.noctuagames.labs.sdk.data.local.dao.ExternalEventDao
import com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity

@Database(
    entities = [EventEntity::class, ExternalEventEntity::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = NoctuaDatabase.Migration1To2::class)
    ]
)
@TypeConverters(StringListTypeConverter::class)
@ConstructedBy(NoctuaDatabaseConstructor::class)
abstract class NoctuaDatabase: RoomDatabase() {
    abstract val eventDao: EventDao
    abstract val externalEventDao: ExternalEventDao

    companion object {
        const val DB_NAME = "noctua.db"
    }

    @RenameColumn(
        tableName = "external_events",
        fromColumnName = "events",
        toColumnName = "event_json"
    )
    class Migration1To2 : AutoMigrationSpec
}
