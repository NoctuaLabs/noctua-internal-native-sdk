package com.noctuagames.sdk.example

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class ComposeUITest {

    @Test
    fun testEventTrackingSectionRendersButtons() = runComposeUiTest {
        setContent {
            MaterialTheme {
                EventTrackingSection(onLog = {})
            }
        }

        onNodeWithText("Event Tracking").assertIsDisplayed()
        onNodeWithText("Track Custom Event").assertIsDisplayed()
        onNodeWithText("Track Revenue Event").assertIsDisplayed()
    }

    @Test
    fun testSessionManagementSectionRendersButtons() = runComposeUiTest {
        setContent {
            MaterialTheme {
                SessionManagementSection(onLog = {})
            }
        }

        onNodeWithText("Session Management").assertIsDisplayed()
        onNodeWithText("Pause").assertIsDisplayed()
        onNodeWithText("Resume").assertIsDisplayed()
    }

    @Test
    fun testExternalEventsSectionRendersButtons() = runComposeUiTest {
        setContent {
            MaterialTheme {
                ExternalEventsSection(onLog = {})
            }
        }

        onNodeWithText("External Events").assertIsDisplayed()
        onNodeWithText("Insert External Event").assertIsDisplayed()
        onNodeWithText("Get Event Count").assertIsDisplayed()
    }

    @Test
    fun testExperimentsSectionRendersButton() = runComposeUiTest {
        setContent {
            MaterialTheme {
                ExperimentsSection(onLog = {})
            }
        }

        onNodeWithText("Experiments").assertIsDisplayed()
        onNodeWithText("Set Experiment + Tag").assertIsDisplayed()
    }

    @Test
    fun testLogOutputSectionRendersMessages() = runComposeUiTest {
        setContent {
            MaterialTheme {
                LogOutputSection(logMessages = mutableStateListOf("SDK initialized", "Test log message"))
            }
        }

        onNodeWithText("Log").assertIsDisplayed()
        onNodeWithText("SDK initialized").assertIsDisplayed()
        onNodeWithText("Test log message").assertIsDisplayed()
    }

    @Test
    fun testLogOutputSectionUpdatesWithNewMessages() = runComposeUiTest {
        val logMessages = mutableStateListOf("First message")

        setContent {
            MaterialTheme {
                LogOutputSection(logMessages = logMessages)
            }
        }

        onNodeWithText("First message").assertIsDisplayed()

        logMessages.add("Second message")
        waitForIdle()

        onNodeWithText("Second message").assertIsDisplayed()
    }
}
