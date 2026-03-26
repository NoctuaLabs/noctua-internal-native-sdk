package com.noctuagames.labs.sdk.utils

import platform.Foundation.NSLog

actual object AppLogger {

    actual var enabled: Boolean = false

    actual fun e(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) {
            NSLog("ERROR: [$tag] $message. Throwable: $throwable CAUSE ${throwable.cause}")
        } else {
            NSLog("ERROR: [$tag] $message")
        }
    }

    actual fun d(tag: String, message: String) {
        if (!enabled) return
        NSLog("DEBUG: [$tag] $message")
    }

    actual fun i(tag: String, message: String) {
        if (!enabled) return
        NSLog("INFO: [$tag] $message")
    }
}
