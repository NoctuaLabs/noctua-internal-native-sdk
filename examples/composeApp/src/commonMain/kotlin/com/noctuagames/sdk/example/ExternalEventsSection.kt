package com.noctuagames.sdk.example

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.noctuagames.labs.sdk.NoctuaInternal

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

    Button(
        onClick = {
            NoctuaInternal.getExternalEventsBatch(limit = 20, offset = 0) { json ->
                if (json == "[]") {
                    onLog("External events: (empty)")
                } else {
                    onLog("External events (first 20): $json")
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Get Events List")
    }

    Button(
        onClick = {
            NoctuaInternal.deleteExternalEvents()
            onLog("Deleted all external events")
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Delete All External Events")
    }
}
