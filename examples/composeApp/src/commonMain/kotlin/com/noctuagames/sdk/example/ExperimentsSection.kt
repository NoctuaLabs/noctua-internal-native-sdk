package com.noctuagames.sdk.example

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.noctuagames.labs.sdk.NoctuaInternal

/**
 * Demonstrates experiment and session tag management via the Noctua SDK.
 */
@Composable
fun ExperimentsSection(onLog: (String) -> Unit) {
    Text("Experiments", style = MaterialTheme.typography.titleMedium)

    Button(
        onClick = {
            NoctuaInternal.setExperiment("compose-ab-test")
            NoctuaInternal.setSessionTag("onboarding")
            onLog("Set experiment: ${NoctuaInternal.getExperiment()}")
            onLog("Set session tag: ${NoctuaInternal.getSessionTag()}")
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Set Experiment + Tag")
    }
}
