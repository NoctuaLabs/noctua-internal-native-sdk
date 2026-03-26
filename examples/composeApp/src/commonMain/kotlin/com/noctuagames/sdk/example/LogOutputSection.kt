package com.noctuagames.sdk.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Displays a scrollable log of SDK actions performed during the session.
 */
@Composable
fun LogOutputSection(logMessages: SnapshotStateList<String>) {
    Text("Log", style = MaterialTheme.typography.titleMedium)

    Column {
        logMessages.forEach { message ->
            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    Spacer(modifier = Modifier.height(32.dp))
}
