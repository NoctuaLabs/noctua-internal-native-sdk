package com.noctuagames.labs.sdk.utils

import android.util.Log

actual object AppLogger {

    actual var enabled: Boolean = false

    actual fun e(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) {
            Log.e(tag, message, throwable)
        } else {
            Log.e(tag, message)
        }
    }

    actual fun d(tag: String, message: String) {
        if (!enabled) return
        Log.d(tag, message)
    }

    actual fun i(tag: String, message: String) {
        if (!enabled) return
        Log.i(tag, message)
    }
}
