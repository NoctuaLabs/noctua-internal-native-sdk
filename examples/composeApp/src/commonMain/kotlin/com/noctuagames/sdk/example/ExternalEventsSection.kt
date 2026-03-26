package com.noctuagames.sdk.example

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.noctuagames.labs.sdk.NoctuaInternal

/**
 * Demonstrates external event storage and batch querying via the Noctua SDK.
 */
@Composable
fun ExternalEventsSection(onLog: (String) -> Unit) {
    Text("External Events", style = MaterialTheme.typography.titleMedium)

    Button(
        onClick = {
            NoctuaInternal.insertExternalEvent(
                """{"source":"compose_example","type":"ad_view"}"""
            )
            onLog("Inserted external event")
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Insert External Event")
    }

    Button(
        onClick = {
            NoctuaInternal.getExternalEventCount { count ->
                onLog("External event count: $count")
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Get Event Count")
    }
}
