package gg.noctua.internal.utils

expect class DeviceUtils() {
    val platform: String
    val deviceId: String
    val gameVersion: String
    val deviceModel: String
    val bundleId: String
    val language: String
    val country: String
    val currency: String
    val acceptLanguage: String
    val osAgent: String
}