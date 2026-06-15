package com.noctuagames.labs.sdk.utils

import kotlin.concurrent.Volatile

/**
 * Process-wide holder for the host-supplied `sandboxEnabled` override, decoupled from
 * Koin so it can be set regardless of how the SDK is initialized (manifest auto-init,
 * `initKoinManually`, or a host's own `Application`).
 *
 * The override always wins over `noctuagg.json`; when no override is set the bundled
 * config value is used, defaulting to `false`. See [resolveSandbox].
 */
object SandboxState {

    @Volatile
    private var override: Boolean? = null

    /**
     * Sets the host override. Takes effect immediately for [AppLogger] and for the
     * `is_sandbox` flag on subsequently-tracked events. Note: HTTP verbose logging is
     * configured once when the client is built, so set this before the first network
     * use (e.g. right after init) for it to affect request logging.
     */
    fun setOverride(enabled: Boolean) {
        override = enabled
        AppLogger.enabled = enabled
    }

    /** The current override, or null when the host has not set one. */
    fun overrideOrNull(): Boolean? = override

    /** Effective sandbox flag: override wins, else [config], else false. */
    fun resolve(config: Boolean?): Boolean = resolveSandbox(override, config)

    /** Test hook: clears the override so cases don't leak across tests. */
    internal fun reset() {
        override = null
    }
}
