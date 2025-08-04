package gg.noctua.analytics.utils

import platform.Foundation.NSBundle
import platform.Foundation.NSLocale
import platform.Foundation.NSLocaleCurrencyCode
import platform.Foundation.NSUUID
import platform.Foundation.countryCode
import platform.Foundation.currentLocale
import platform.Foundation.preferredLanguages
import platform.UIKit.UIDevice

actual class DeviceUtils {

    actual val platform: String
        get() = "iOS"

    actual val deviceId: String
        get() = UIDevice.currentDevice.identifierForVendor?.UUIDString ?: NSUUID().UUIDString()

    actual val gameVersion: String
        get() = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleShortVersionString")?.toString() ?: "unknown"

    actual val deviceModel: String
        get() = UIDevice.currentDevice.model

    actual val bundleId: String
        get() = NSBundle.mainBundle.bundleIdentifier ?: "unknown"

    actual val language: String
        get() = (NSLocale.preferredLanguages.firstOrNull() as? String)?.substringBefore("-") ?: "en"

    actual val country: String
        get() = NSLocale.currentLocale.countryCode ?: "US"

    actual val currency: String
        get() = (NSLocale.currentLocale.objectForKey(NSLocaleCurrencyCode) as? String) ?: "USD"

    actual val acceptLanguage: String
        get() = NSLocale.preferredLanguages.joinToString(",")

    actual val osAgent: String
        get() = "iOS ${UIDevice.currentDevice.systemVersion} (${UIDevice.currentDevice.systemName})"
}