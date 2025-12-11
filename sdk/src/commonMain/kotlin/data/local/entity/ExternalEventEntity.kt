package com.noctuagames.labs.sdk.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "external_events")
data class ExternalEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val events: String,
)