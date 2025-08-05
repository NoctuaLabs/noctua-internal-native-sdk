package gg.noctua.internal.utils

import gg.noctua.internal.data.models.NoctuaConfig
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.serialization.json.Json
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.stringWithContentsOfFile

actual object AppContext

@OptIn(ExperimentalForeignApi::class)
actual  fun loadAppConfig(): NoctuaConfig {
    val filePath = NSBundle.mainBundle.pathForResource("noctuagg", "json")
        ?: throw IllegalArgumentException("noctuagg.json not found in bundle")

    val content = NSString.stringWithContentsOfFile(filePath, NSUTF8StringEncoding, null)
        ?: throw IllegalArgumentException("Failed to read noctuagg.json")

    return Json.decodeFromString(NoctuaConfig.serializer(), content)
}

actual fun getPlatformType(): String {
    return PlatformType.appstore.name
}