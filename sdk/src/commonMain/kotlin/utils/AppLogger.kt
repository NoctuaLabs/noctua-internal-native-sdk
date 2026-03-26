package com.noctuagames.labs.sdk.utils

/**
 * Cross-platform logger for the Noctua SDK.
 *
 * When [enabled] is `false` (production / sandbox disabled), only error-level
 * messages are emitted. Debug and info calls are no-ops.
 *
 * Set [enabled] = `true` when `sandboxEnabled` is `true` in the config to
 * get full debug output during development and QA.
 */
expect object AppLogger {
    /** Controls whether debug/info logs are emitted. Errors always log. */
    var enabled: Boolean

    fun e(tag: String, message: String, throwable: Throwable? = null)
    fun d(tag: String, message: String)
    fun i(tag: String, message: String)
}
