package com.noctuagames.labs.sdk.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<NoctuaDatabase>
}