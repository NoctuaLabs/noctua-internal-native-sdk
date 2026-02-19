package com.noctuagames.labs.sdk.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import com.noctuagames.labs.sdk.data.database.dao.EventDao
import com.noctuagames.labs.sdk.data.database.entity.EventEntity
import com.noctuagames.labs.sdk.data.local.dao.ExternalEventDao
import com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity

@Database(
    entities = [EventEntity::class, ExternalEventEntity::class],
    version = 2
)
@TypeConverters(StringListTypeConverter::class)
@ConstructedBy(NoctuaDatabaseConstructor::class)
abstract class NoctuaDatabase: RoomDatabase() {
    abstract val eventDao: EventDao
    abstract val externalEventDao: ExternalEventDao

    companion object {
        const val DB_NAME = "noctua.db"

        /**
         * Migration from v1 to v2:
         * - Renames 'events' column to 'event_json' in external_events table
         * - Adds 'created_at' column
         * - Changes from single-blob storage to per-row event storage
         *
         * Note: Existing blob rows are migrated as-is into the new schema.
         * Unity-side migration will unpack them if needed.
         */
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(connection: SQLiteConnection) {
                // 1. Create new table with updated schema
                connection.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS external_events_new (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        event_json TEXT NOT NULL,
                        created_at INTEGER NOT NULL DEFAULT 0
                    )
                    """.trimIndent()
                )

                // 2. Migrate existing blob rows: each row's 'events' column contains
                //    a JSON string (the entire blob from Unity). Copy as-is into
                //    a single row in new table — Unity-side migration will unpack it
                connection.execSQL(
                    """
                    INSERT INTO external_events_new (event_json, created_at)
                    SELECT events, 0 FROM external_events
                    """.trimIndent()
                )

                // 3. Drop old table, rename new
                connection.execSQL("DROP TABLE external_events")
                connection.execSQL("ALTER TABLE external_events_new RENAME TO external_events")
            }
        }
    }
}
