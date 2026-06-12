package com.noctuagames.labs.sdk.utils

import com.noctuagames.labs.sdk.data.models.NoctuaConfig
import com.noctuagames.labs.sdk.di.initKoin
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UIntVar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

private var lifecycleObserver: IOSLifecycleObserver? = null

fun initKoinManually() {
    initKoin()

    // Start observing iOS lifecycle notifications so that session
    // pause/resume and heartbeat work automatically without requiring
    // the host app to call onInternalNoctuaApplicationPause manually.
    lifecycleObserver = IOSLifecycleObserver().also { it.startObserving() }
}
actual object AppContext

actual fun disposePlatformLifecycle() {
    lifecycleObserver?.stopObserving()
    lifecycleObserver = null
}

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
        ignoreUnknownKeys = true   // tolerate extra keys
        coerceInputValues = true   // explicit null on a non-null-with-default → use default
        isLenient = true
    }

    return json.decodeFromString(NoctuaConfig.serializer(), content)
}


actual fun getPlatformType(): String {
    return PlatformType.appstore.name
}

// SCNetworkReachabilityGetFlags can block (synchronous DNS on first use), so
// run it off the caller's dispatcher. Dispatchers.IO is unavailable on Native;
// Default is the correct choice here.
@OptIn(ExperimentalForeignApi::class)
actual suspend fun isNetworkAvailable(): Boolean = withContext(Dispatchers.Default) {
    memScoped {
        val reachability = SCNetworkReachabilityCreateWithName(null, "www.google.com")
            ?: return@memScoped false

        val flagsVar = alloc<UIntVar>()
        val gotFlags = SCNetworkReachabilityGetFlags(reachability, flagsVar.ptr)

        if (!gotFlags) return@memScoped false

        val flags = flagsVar.value.toInt()
        val reachable = flags and kSCNetworkFlagsReachable.toInt() != 0
        val connectionRequired = flags and kSCNetworkFlagsConnectionRequired.toInt() != 0

        reachable && !connectionRequired
    }
}
