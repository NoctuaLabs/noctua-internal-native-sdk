package gg.noctua.analytics.utils

import java.util.*
import java.net.InetAddress
import java.net.NetworkInterface

actual class DeviceUtils {

    actual val platform: String
        get() = "Desktop"
    actual val deviceId: String
        get() = getMacAddress() ?: UUID.randomUUID().toString()
    actual val gameVersion: String
        get() = "1.0.0"
    actual val deviceModel: String
        get() = System.getProperty("os.name") + " " + System.getProperty("os.version")

    private fun getMacAddress(): String? {
        return try {
            val network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost())
            val mac = network.hardwareAddress ?: return null
            mac.joinToString(":") { "%02X".format(it) }
        } catch (e: Exception) {
            null
        }
    }

    actual val bundleId: String
        get() = "com.noctuagames.desktop"

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
        get() = "${System.getProperty("os.name")} ${System.getProperty("os.version")} (${System.getProperty("os.arch")})"
}