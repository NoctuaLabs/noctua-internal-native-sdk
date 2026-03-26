package com.noctuagames.sdk.example

import androidx.compose.ui.window.ComposeUIViewController
import com.noctuagames.labs.sdk.utils.initKoinManually

/**
 * iOS entry point for Compose Multiplatform.
 *
 * Called from the Swift side to create the Compose UI view controller.
 * `initKoinManually()` initializes the SDK and starts the iOS lifecycle
 * observer that handles session pause/resume automatically.
 */
fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoinManually()
    }
) {
    App()
}
