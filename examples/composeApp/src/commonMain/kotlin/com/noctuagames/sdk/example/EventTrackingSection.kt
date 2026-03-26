package com.noctuagames.sdk.example

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.noctuagames.labs.sdk.NoctuaInternal

/**
 * Demonstrates custom event and revenue event tracking via the Noctua SDK.
 */
@Composable
fun EventTrackingSection(onLog: (String) -> Unit) {
    Text("Event Tracking", style = MaterialTheme.typography.titleMedium)

    Button(
        onClick = {
            NoctuaInternal.trackCustomEvent(
                eventName = "button_click",
                properties = mapOf("button" to "track_event", "screen" to "main")
            )
            onLog("Tracked: button_click")
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Track Custom Event")
    }

    Button(
        onClick = {
            NoctuaInternal.trackCustomEventWithRevenue(
                eventName = "purchase",
                revenue = 4.99,
                currency = "USD",
                properties = mapOf("item" to "premium_skin")
            )
            onLog("Tracked: purchase (\$4.99 USD)")
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Track Revenue Event")
    }
}
