package com.noctuagames.internal.sdk.data.models

import kotlinx.serialization.Serializable

@Serializable
internal data class EventResponse(
    val success: Boolean,
    val data: EventData
)

@Serializable
internal data class EventData(
    val message: String
)
