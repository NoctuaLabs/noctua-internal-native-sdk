package com.noctuagames.labs.sdk.utils

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import java.util.Currency
import java.util.Locale

actual class DeviceUtils {

    actual val platform: String
        get() = "Android"

    actual val deviceId: String
        @SuppressLint("HardwareIds")
        get() = Settings.Secure.getString(
            AppContext.get().contentResolver,
            Settings.Secure.ANDROID_ID
        ) ?: "unknown"

    actual val gameVersion: String
        get() = try {
            val packageInfo = AppContext.get().packageManager.getPackageInfo(AppContext.get().packageName, 0)
            packageInfo.versionName ?: "unknown"
        } catch (e: PackageManager.NameNotFoundException) {
            "unknown"
        }

    actual val deviceModel: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"

    actual val bundleId: String
        get() = AppContext.get().packageName

    actual val language: String
        get() = Locale.getDefault().language

    actual val country: String
        get() = Locale.getDefault().country

    actual val currency: String
        get() = try {
            Currency.getInstance(Locale.getDefault()).currencyCode
        } catch (e: Exception) {
            "USD"
        }

    actual val acceptLanguage: String
        get() = Locale.getDefault().toLanguageTag()

    actual val osAgent: String
        get() = "Android ${Build.VERSION.RELEASE} / API-${Build.VERSION.SDK_INT} (${Build.FINGERPRINT})"
}