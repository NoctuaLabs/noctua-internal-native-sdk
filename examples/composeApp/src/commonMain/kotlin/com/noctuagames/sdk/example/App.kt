package com.noctuagames.sdk.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Shared Compose Multiplatform UI for the Noctua SDK example app.
 *
 * Orchestrates section components that each demonstrate a specific SDK feature:
 *  - [EventTrackingSection] — custom and revenue event tracking
 *  - [SessionManagementSection] — session pause/resume
 *  - [ExternalEventsSection] — external event storage and querying
 *  - [ExperimentsSection] — experiment and session tag management
 *  - [LogOutputSection] — live log of SDK actions
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val logMessages = remember { mutableStateListOf("SDK initialized") }

    val onLog: (String) -> Unit = { message -> logMessages.add(message) }

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Noctua SDK Example") })
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                EventTrackingSection(onLog = onLog)

                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                SessionManagementSection(onLog = onLog)

                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                ExternalEventsSection(onLog = onLog)

                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                ExperimentsSection(onLog = onLog)

                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                LogOutputSection(logMessages = logMessages)
            }
        }
    }
}
