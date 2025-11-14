package com.noctuagames.labs.sdk.utils

import com.noctuagames.labs.sdk.data.models.NoctuaConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File
import java.net.InetSocketAddress
import java.net.Socket

actual object AppContext

actual fun loadAppConfig(): NoctuaConfig {
    val file = File("src/jvmMain/resources/noctuagg.json")
    val json = file.readText()
    return Json.decodeFromString<NoctuaConfig>(json)
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