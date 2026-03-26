package com.noctuagames.sdk.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.noctuagames.labs.sdk.NoctuaInternal

/**
 * Android entry point using Compose Multiplatform shared UI.
 *
 * Lifecycle events are forwarded to the SDK for proper session tracking.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }

    override fun onPause() {
        super.onPause()
        NoctuaInternal.onInternalNoctuaApplicationPause(pauseStatus = true)
    }

    override fun onResume() {
        super.onResume()
        NoctuaInternal.onInternalNoctuaApplicationPause(pauseStatus = false)
    }

    override fun onDestroy() {
        super.onDestroy()
        NoctuaInternal.onInternalNoctuaDispose()
    }
}
