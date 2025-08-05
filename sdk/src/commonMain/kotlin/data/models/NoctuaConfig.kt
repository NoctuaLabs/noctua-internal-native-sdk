package com.noctuagames.internal.sdk.data.models

import kotlinx.serialization.Serializable

@Serializable
data class NoctuaConfig(
    val productCode: String? = "",
    val clientId: String,
    val adjust: PlatformAdjustConfig?,
    val facebook: PlatformFacebookConfig?,
    val firebase: PlatformFirebaseConfig?,
    val noctua: NoctuaFeatureConfig?
)

@Serializable
data class PlatformAdjustConfig(
    val android: AdjustPlatformConfig,
    val ios: AdjustPlatformConfig
)

@Serializable
data class AdjustPlatformConfig(
    val appToken: String,
    val eventMap: Map<String, String>
)

@Serializable
data class PlatformFacebookConfig(
    val android: FacebookPlatformConfig,
    val ios: FacebookPlatformConfig
)

@Serializable
data class FacebookPlatformConfig(
    val appId: String,
    val clientToken: String,
    val displayName: String,
    val enableDebug: Boolean
)

@Serializable
data class PlatformFirebaseConfig(
    val android: FirebasePlatformConfig,
    val ios: FirebasePlatformConfig
)

@Serializable
data class FirebasePlatformConfig(
    val customEventDisabled: Boolean
)

@Serializable
data class NoctuaFeatureConfig(
    val sentryDsnUrl: String,
    val sandboxEnabled: Boolean? = false,
    val offlineFirstEnabled: Boolean,
    val welcomeToastDisabled: Boolean,
    val iaaEnabled: Boolean,
    val iapDisabled: Boolean,
    val remoteFeatureFlags: RemoteFeatureFlags
)

@Serializable
data class RemoteFeatureFlags(
    val ssoDisabled: Boolean,
    val vnLegalPurposeEnabled: Boolean,
    val vnLegalPurposeFullKycEnabled: Boolean,
    val vnLegalPurposePhoneNumberVerificationEnabled: Boolean
)
