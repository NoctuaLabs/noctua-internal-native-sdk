package com.noctuagames.labs.sdk.utils

import com.noctuagames.labs.sdk.data.models.NoctuaConfig
import com.noctuagames.labs.sdk.di.initKoin
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UIntVar
import kotlinx.serialization.json.Json
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.stringWithContentsOfFile
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.SystemConfiguration.SCNetworkReachabilityCreateWithName
import platform.SystemConfiguration.SCNetworkReachabilityFlags
import platform.SystemConfiguration.SCNetworkReachabilityGetFlags
import platform.SystemConfiguration.kSCNetworkFlagsConnectionRequired
import platform.SystemConfiguration.kSCNetworkFlagsReachable

fun initKoinManually() {
    initKoin()
}
actual object AppContext

@OptIn(ExperimentalForeignApi::class)
actual fun loadAppConfig(): NoctuaConfig {
    val filePath = NSBundle.mainBundle.pathForResource("noctuagg", "json")
        ?: throw IllegalArgumentException("noctuagg.json not found in bundle")

    val content = NSString.stringWithContentsOfFile(
        filePath,
        NSUTF8StringEncoding,
        null
    ) ?: throw IllegalArgumentException("Failed to read noctuagg.json")

    val json = Json {
        ignoreUnknownKeys = true
    }

    return json.decodeFromString(NoctuaConfig.serializer(), content)
}


actual fun getPlatformType(): String {
    return PlatformType.appstore.name
}

@OptIn(ExperimentalForeignApi::class)
actual suspend fun isNetworkAvailable(): Boolean = memScoped {
    val reachability = SCNetworkReachabilityCreateWithName(null, "www.google.com")
        ?: return false

    val flagsVar = alloc<UIntVar>()
    val gotFlags = SCNetworkReachabilityGetFlags(reachability, flagsVar.ptr)

    if (!gotFlags) return false

    val flags = flagsVar.value.toInt()
    val reachable = flags and kSCNetworkFlagsReachable.toInt() != 0
    val connectionRequired = flags and kSCNetworkFlagsConnectionRequired.toInt() != 0

    return reachable && !connectionRequired
}
