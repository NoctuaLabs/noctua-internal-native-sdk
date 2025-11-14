package com.noctuagames.labs.sdk.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

object StringListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun mapToString(map: Map<String, Any>?): String? =
        map?.let { Json.encodeToString(it) }

    @TypeConverter
    fun stringToMap(string: String?): Map<String, Any>? =
        string?.let { Json.decodeFromString(it) }
}