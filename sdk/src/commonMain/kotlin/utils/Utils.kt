package gg.noctua.internal.utils

import gg.noctua.internal.data.models.NoctuaConfig
import kotlinx.datetime.LocalDateTime
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

expect fun loadAppConfig(): NoctuaConfig

enum class PlatformType {
    playstore, appstore, direct
}

expect fun getPlatformType() : String

@OptIn(ExperimentalTime::class)
fun getCurrentDateTimestamp(): String {
    val now: Instant = Clock.System.now()
    return now.toString()
}

val json = Json { prettyPrint = false }

@OptIn(ExperimentalTime::class)
fun mapToJsonString(map: Map<String, Any>): String {
    fun toJsonElement(value: Any?): JsonElement = when (value) {
        null -> JsonNull
        is String -> JsonPrimitive(value)
        is Number -> JsonPrimitive(value)
        is Boolean -> JsonPrimitive(value)
        is Instant -> JsonPrimitive(value.toString())
        is LocalDateTime -> JsonPrimitive(value.toString())
        is Map<*, *> -> JsonObject(value.entries.associate {
            it.key.toString() to toJsonElement(it.value)
        })
        is List<*> -> JsonArray(value.map { toJsonElement(it) })
        else -> throw IllegalArgumentException("Unsupported type: ${value::class}")
    }

    val jsonMap = map.mapValues { toJsonElement(it.value) }
    return json.encodeToString(JsonObject(jsonMap))
}

fun additionalParams(deviceUtils: DeviceUtils, noctuaConfig: NoctuaConfig): Map<String, Any> {
    val eventPayload = mutableMapOf<String, Any>(
        "event_version" to 1,
        "sdk_version" to "1.0.0",
        "device_id" to deviceUtils.deviceId,
        "device_os_version" to deviceUtils.osAgent,
        "device_os" to "Other",
        "device_type" to "Handheld",
        "device_model" to deviceUtils.deviceModel,
        "bundle_id" to deviceUtils.bundleId,
        "game_version" to deviceUtils.gameVersion,
        "country" to deviceUtils.country,
        "ipAddress" to "0.0.0.0",
        "is_sandbox" to (noctuaConfig.noctua?.sandboxEnabled ?: false),
        "timestamp" to getCurrentDateTimestamp()
    )
    return eventPayload
}
