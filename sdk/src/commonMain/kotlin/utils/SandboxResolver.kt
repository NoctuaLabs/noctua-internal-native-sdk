package com.noctuagames.labs.sdk.utils

/**
 * Resolves the effective `sandboxEnabled` flag, mirroring the native-sdk contract:
 * a host-supplied init override wins over the bundled `noctuagg.json` value. When
 * both are absent the SDK defaults to `false` (sandbox off) — verbose logging and
 * the `is_sandbox` event property stay off unless explicitly turned on.
 *
 * @param override host-supplied value passed to `initKoin`/`initKoinManually`; null when not provided.
 * @param config the `noctua.sandboxEnabled` value parsed from `noctuagg.json`; null when absent.
 */
internal fun resolveSandbox(override: Boolean?, config: Boolean?): Boolean =
    override ?: config ?: false
