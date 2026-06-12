package com.noctuagames.labs.sdk.utils

import com.noctuagames.labs.sdk.data.models.NoctuaConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File
import java.net.InetSocketAddress
import java.net.Socket

actual object AppContext

actual fun disposePlatformLifecycle() {}

actual fun loadAppConfig(): NoctuaConfig {
    // Classpath first (works in a packaged JAR), dev-time file path as fallback
    val json = object {}.javaClass.classLoader
        ?.getResourceAsStream("noctuagg.json")
        ?.use { it.bufferedReader().readText() }
        ?: File("src/jvmMain/resources/noctuagg.json").takeIf { it.exists() }?.readText()
        ?: throw IllegalArgumentException("noctuagg.json not found on classpath or in src/jvmMain/resources")

    val parser = Json {
        ignoreUnknownKeys = true   // tolerate extra keys
        coerceInputValues = true   // explicit null on a non-null-with-default → use default
        isLenient = true
    }
    return parser.decodeFromString<NoctuaConfig>(json)
}

actual fun getPlatformType(): String {
    return PlatformType.direct.name
}

actual suspend fun isNetworkAvailable(): Boolean = withContext(Dispatchers.IO) {
    return@withContext try {
        Socket().use { socket ->
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            true
        }
    } catch (e: Exception) {
        false
    }
}