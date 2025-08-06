package com.noctuagames.labs.sdk.utils

import com.noctuagames.labs.sdk.data.models.NoctuaConfig
import kotlinx.serialization.json.Json
import java.io.File

actual object AppContext

actual fun loadAppConfig(): NoctuaConfig {
    val file = File("src/jvmMain/resources/noctuagg.json")
    val json = file.readText()
    return Json.decodeFromString<NoctuaConfig>(json)
}

actual fun getPlatformType(): String {
    return PlatformType.direct.name
}