package com.noctuagames.labs.sdk.models

import com.noctuagames.labs.sdk.data.models.NoctuaConfig
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Guards that a partial / minimal / evolving noctuagg.json never throws.
 * Every field has a default, so missing keys are tolerated; the parser config
 * mirrors the platform loaders (ignoreUnknownKeys + coerceInputValues + isLenient).
 */
class NoctuaConfigParsingTest {

    private val parser = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }

    @Test
    fun emptyObject_parsesToAllDefaults() {
        val config = parser.decodeFromString<NoctuaConfig>("{}")
        assertNull(config.clientId)
        assertNull(config.gameId)
        assertNull(config.noctua)
    }

    @Test
    fun partialObject_missingKeysDoNotThrow() {
        val config = parser.decodeFromString<NoctuaConfig>(
            """{ "clientId": "abc", "noctua": { "sandboxEnabled": true } }"""
        )
        assertEquals("abc", config.clientId)
        assertNull(config.gameId)
        assertEquals(true, config.noctua?.sandboxEnabled)
        assertNull(config.noctua?.iaaEnabled)
    }

    @Test
    fun unknownKeys_areIgnored() {
        val config = parser.decodeFromString<NoctuaConfig>(
            """{ "clientId": "abc", "someFutureField": 123, "nested": { "x": 1 } }"""
        )
        assertEquals("abc", config.clientId)
    }

    @Test
    fun explicitNullOnNonNullableWithDefault_coercesToDefault() {
        // eventMap is non-nullable with an emptyMap() default; an explicit null
        // would throw without coerceInputValues
        val config = parser.decodeFromString<NoctuaConfig>(
            """{ "adjust": { "android": { "appToken": "t", "eventMap": null } } }"""
        )
        assertTrue(config.adjust?.android?.eventMap?.isEmpty() == true)
    }

    @Test
    fun fullyPopulatedConfig_parsesEveryNestedField() {
        val config = parser.decodeFromString<NoctuaConfig>(
            """
            {
              "clientId": "client-123",
              "gameId": 42,
              "adjust": {
                "android": { "appToken": "a-tok", "eventMap": { "purchase": "abc123" } },
                "ios": { "appToken": "i-tok" }
              },
              "facebook": {
                "android": { "appId": "fb-a", "clientToken": "ct-a", "displayName": "Game", "enableDebug": true },
                "ios": { "appId": "fb-i", "clientToken": "ct-i", "displayName": "Game", "enableDebug": false }
              },
              "firebase": {
                "android": { "customEventDisabled": true },
                "ios": { "customEventDisabled": false }
              },
              "noctua": {
                "sentryDsnUrl": "https://x@glitchtip/1",
                "sandboxEnabled": true,
                "offlineFirstEnabled": true,
                "welcomeToastDisabled": false,
                "iaaEnabled": true,
                "iapDisabled": false,
                "nativeInternalTrackerEnabled": true,
                "remoteFeatureFlags": {
                  "ssoDisabled": true,
                  "vnLegalPurposeEnabled": true,
                  "vnLegalPurposeFullKycEnabled": false,
                  "vnLegalPurposePhoneNumberVerificationEnabled": true
                }
              }
            }
            """.trimIndent()
        )

        assertEquals("client-123", config.clientId)
        assertEquals(42L, config.gameId)
        assertEquals("a-tok", config.adjust?.android?.appToken)
        assertEquals("abc123", config.adjust?.android?.eventMap?.get("purchase"))
        assertEquals("fb-a", config.facebook?.android?.appId)
        assertEquals(true, config.facebook?.android?.enableDebug)
        assertEquals(true, config.firebase?.android?.customEventDisabled)
        assertEquals(false, config.firebase?.ios?.customEventDisabled)
        assertEquals(true, config.noctua?.nativeInternalTrackerEnabled)
        assertEquals(true, config.noctua?.remoteFeatureFlags?.ssoDisabled)
        assertEquals(false, config.noctua?.remoteFeatureFlags?.vnLegalPurposeFullKycEnabled)
        assertEquals(true, config.noctua?.remoteFeatureFlags?.vnLegalPurposePhoneNumberVerificationEnabled)
    }

    @Test
    fun nestedPartialConfig_parsesWithoutAllSiblings() {
        val config = parser.decodeFromString<NoctuaConfig>(
            """{ "facebook": { "android": { "appId": "1" } } }"""
        )
        assertEquals("1", config.facebook?.android?.appId)
        assertNull(config.facebook?.android?.clientToken)
        assertNull(config.facebook?.ios)
    }
}
