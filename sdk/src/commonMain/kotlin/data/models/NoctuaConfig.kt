package com.noctuagames.labs.sdk.data.models

import kotlinx.serialization.Serializable

// Every field carries a default so a missing key in noctuagg.json is tolerated
// rather than throwing MissingFieldException. Combined with ignoreUnknownKeys
// on the parsers, this makes config loading resilient to partial/evolving files.

@Serializable
data class NoctuaConfig(
    val clientId: String? = null,
    val gameId: Long? = null,
    val adjust: PlatformAdjustConfig? = null,
    val facebook: PlatformFacebookConfig? = null,
    val firebase: PlatformFirebaseConfig? = null,
    val noctua: NoctuaFeatureConfig? = null
)

@Serializable
data class PlatformAdjustConfig(
    val android: AdjustPlatformConfig? = null,
    val ios: AdjustPlatformConfig? = null
)

@Serializable
data class AdjustPlatformConfig(
    val appToken: String? = null,
    val eventMap: Map<String, String> = emptyMap()
)

@Serializable
data class PlatformFacebookConfig(
    val android: FacebookPlatformConfig? = null,
    val ios: FacebookPlatformConfig? = null
)

@Serializable
data class FacebookPlatformConfig(
    val appId: String? = null,
    val clientToken: String? = null,
    val displayName: String? = null,
    val enableDebug: Boolean? = null
)

@Serializable
data class PlatformFirebaseConfig(
    val android: FirebasePlatformConfig? = null,
    val ios: FirebasePlatformConfig? = null
)

@Serializable
data class FirebasePlatformConfig(
    val customEventDisabled: Boolean? = null
)

@Serializable
data class NoctuaFeatureConfig(
    val sentryDsnUrl: String? = null,
    val sandboxEnabled: Boolean? = false,
    val offlineFirstEnabled: Boolean? = null,
    val welcomeToastDisabled: Boolean? = null,
    val iaaEnabled: Boolean? = null,
    val iapDisabled: Boolean? = null,
    // Master switch for the native internal tracker. When true, the SDK also
    // emits noctua_user_engagement paired with each session event.
    val nativeInternalTrackerEnabled: Boolean? = null,
    val remoteFeatureFlags: RemoteFeatureFlags? = null
)

@Serializable
data class RemoteFeatureFlags(
    val ssoDisabled: Boolean? = null,
    val vnLegalPurposeEnabled: Boolean? = null,
    val vnLegalPurposeFullKycEnabled: Boolean? = null,
    val vnLegalPurposePhoneNumberVerificationEnabled: Boolean? = null
)
