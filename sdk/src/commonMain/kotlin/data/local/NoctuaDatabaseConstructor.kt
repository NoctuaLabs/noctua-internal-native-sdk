package com.noctuagames.labs.sdk.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object NoctuaDatabaseConstructor: RoomDatabaseConstructor<NoctuaDatabase> {
    override fun initialize(): NoctuaDatabase
}