package com.noctuagames.sdk.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.noctuagames.labs.sdk.NoctuaInternal

/**
 * Demonstrates manual session pause/resume via the Noctua SDK.
 */
@Composable
fun SessionManagementSection(onLog: (String) -> Unit) {
    Text("Session Management", style = MaterialTheme.typography.titleMedium)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = {
                NoctuaInternal.onInternalNoctuaApplicationPause(pauseStatus = true)
                onLog("Session paused (manual)")
            },
            modifier = Modifier.weight(1f)
        ) {
            Text("Pause")
        }

        Button(
            onClick = {
                NoctuaInternal.onInternalNoctuaApplicationPause(pauseStatus = false)
                onLog("Session resumed (manual)")
            },
            modifier = Modifier.weight(1f)
        ) {
            Text("Resume")
        }
    }
}
