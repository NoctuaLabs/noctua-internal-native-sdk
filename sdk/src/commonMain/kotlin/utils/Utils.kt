package gg.noctua.analytics.utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

expect object AppContext

@OptIn(ExperimentalTime::class)
fun getCurrentDateTimestamp(): String {
    val now: Instant = Clock.System.now()
    return now.toString()
}

val json = Json { prettyPrint = false }

fun mapToJsonString(map: Map<String, Any>): String {
    fun toJsonElement(value: Any?): JsonElement = when (value) {
        null -> JsonNull
        is String -> JsonPrimitive(value)
        is Number -> JsonPrimitive(value)
        is Boolean -> JsonPrimitive(value)
        is Map<*, *> -> JsonObject(value.entries.associate { it.key.toString() to toJsonElement(it.value) })
        is List<*> -> JsonArray(value.map { toJsonElement(it) })
        else -> throw IllegalArgumentException("Unsupported type: ${value::class}")
    }

    val jsonMap = map.mapValues { toJsonElement(it.value) }
    return json.encodeToString(JsonObject(jsonMap))
}

fun additionalParams(deviceUtils: DeviceUtils): Map<String, Any> {
    val eventPayload = mutableMapOf<String, Any>(
        "event_version" to 1,
        "sdk_version" to "0.45.0.0",
        "device_id" to deviceUtils.deviceId,
        "device_os_version" to deviceUtils.osAgent,
        "device_os" to "Other",
        "device_type" to "Handheld",
        "device_model" to deviceUtils.deviceModel,
        "bundle_id" to deviceUtils.bundleId,
        "game_version" to deviceUtils.gameVersion,
        "country" to deviceUtils.country,
        "ipAddress" to "",
        "is_sandbox" to false,
        "timestamp" to getCurrentDateTimestamp()
    )
    return eventPayload
}
