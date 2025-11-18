package com.noctuagames.labs.sdk.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
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

actual fun loadAppConfig(): NoctuaConfig {
    try {
        AppContext.get().assets.open("noctuagg.json").use {
            val json = it.bufferedReader().use { reader -> reader.readText() }
            val jsonParser = Json {
                ignoreUnknownKeys = true
            }
            return jsonParser.decodeFromString<NoctuaConfig>(json)
        }
    } catch (e: IOException) {
        throw IllegalArgumentException("Failed to load noctuagg.json", e)
    }
}

actual fun getPlatformType(): String {
    val installer = try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            AppContext.get().packageManager.getInstallSourceInfo(AppContext.get().packageName)
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

@RequiresApi(Build.VERSION_CODES.M)
actual suspend fun isNetworkAvailable(): Boolean = withContext(Dispatchers.IO) {
    val connectivityManager = AppContext.get().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return@withContext false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return@withContext false
    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}
