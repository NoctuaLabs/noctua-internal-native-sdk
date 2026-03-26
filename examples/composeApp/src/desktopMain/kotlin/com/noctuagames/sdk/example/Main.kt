package com.noctuagames.sdk.example

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.noctuagames.labs.sdk.NoctuaInternal
import com.noctuagames.labs.sdk.di.initKoin

/**
 * Desktop (JVM) entry point using Compose Multiplatform shared UI.
 */
fun main() = application {
    // Initialize SDK
    initKoin()
    NoctuaInternal.onInternalNoctuaApplicationPause(pauseStatus = false)

    Window(
        onCloseRequest = {
            NoctuaInternal.onInternalNoctuaDispose()
            exitApplication()
        },
        title = "Noctua SDK Example (Desktop)"
    ) {
        App()
    }
}
