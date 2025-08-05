package com.noctuagames.internal.sdk.utils

import android.content.Context
import android.os.Build
import com.noctuagames.internal.sdk.data.models.NoctuaConfig
import kotlinx.io.IOException
import kotlinx.serialization.json.Json
import java.lang.ref.WeakReference

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
            return Json.decodeFromString<NoctuaConfig>(json)
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