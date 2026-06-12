package com.noctuagames.labs.sdk.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.noctuagames.labs.sdk.data.models.NoctuaConfig
import com.noctuagames.labs.sdk.di.initKoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.io.IOException
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import java.lang.ref.WeakReference

fun initKoinManually(context: Context) {
    initKoin {
        androidContext(context)
    }
}

actual object AppContext {
    private var value: WeakReference<Context?>? = null

    fun set(context: Context) {
        value = WeakReference(context)
    }
    internal fun get(): Context {
        return value?.get() ?: throw RuntimeException("Context Error")
    }
}

// No platform-managed lifecycle resources on Android: the host registers
// InternalNoctuaApp/Activity callbacks itself.
actual fun disposePlatformLifecycle() {}

actual fun loadAppConfig(): NoctuaConfig {
    try {
        AppContext.get().assets.open("noctuagg.json").use {
            val json = it.bufferedReader().use { reader -> reader.readText() }
            val jsonParser = Json {
                ignoreUnknownKeys = true   // tolerate extra keys
                coerceInputValues = true   // explicit null on a non-null-with-default → use default
                isLenient = true
            }
            return jsonParser.decodeFromString<NoctuaConfig>(json)
        }
    } catch (e: IOException) {
        throw IllegalArgumentException("Failed to load noctuagg.json", e)
    }
}

actual fun getPlatformType(): String {
    val installer: String? = try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            AppContext.get().packageManager
                .getInstallSourceInfo(AppContext.get().packageName)
                .installingPackageName
        } else {
            @Suppress("DEPRECATION")
            AppContext.get().packageManager.getInstallerPackageName(AppContext.get().packageName)
        }
    } catch (e: Exception) {
        throw IllegalArgumentException("Failed to get installer package name", e)
    }

    return when (installer) {
        "com.android.vending" -> PlatformType.playstore.name
        else -> PlatformType.direct.name
    }
}

actual suspend fun isNetworkAvailable(): Boolean = withContext(Dispatchers.IO) {
    val connectivityManager = AppContext.get().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return@withContext false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return@withContext false
        capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        // minSdk is 22; activeNetwork/getNetworkCapabilities require API 23
        @Suppress("DEPRECATION")
        connectivityManager.activeNetworkInfo?.isConnected == true
    }
}
