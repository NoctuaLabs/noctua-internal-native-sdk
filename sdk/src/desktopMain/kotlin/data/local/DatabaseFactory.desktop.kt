package com.noctuagames.labs.sdk.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<NoctuaDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "Noctua")
            os.contains("mac") -> File(userHome, "Library/Application Support/Noctua")
            else -> File(userHome, ".local/share/Noctua")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, NoctuaDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}