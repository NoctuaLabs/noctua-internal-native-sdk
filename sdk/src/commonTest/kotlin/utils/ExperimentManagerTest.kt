package com.noctuagames.labs.sdk.utils

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ExperimentManagerTest {

    @BeforeTest
    fun setup() {
        ExperimentManager.clear()
    }

    @AfterTest
    fun teardown() {
        ExperimentManager.clear()
    }

    @Test
    fun sessionId_defaultsToEmptyString() {
        assertEquals("", ExperimentManager.getSessionId())
    }

    @Test
    fun setSessionId_getSessionId_roundTrip() {
        ExperimentManager.setSessionId("session-123")
        assertEquals("session-123", ExperimentManager.getSessionId())
    }

    @Test
    fun setExperiment_getExperiment_roundTrip() {
        ExperimentManager.setExperiment("exp-a-b-test")
        assertEquals("exp-a-b-test", ExperimentManager.getExperiment())
    }

    @Test
    fun experiment_defaultsToEmptyString() {
        assertEquals("", ExperimentManager.getExperiment())
    }

    @Test
    fun setSessionTag_getSessionTag_roundTrip() {
        ExperimentManager.setSessionTag("feature-x")
        assertEquals("feature-x", ExperimentManager.getSessionTag())
    }

    @Test
    fun sessionTag_defaultsToEmptyString() {
        assertEquals("", ExperimentManager.getSessionTag())
    }

    @Test
    fun setGeneralExperiment_getGeneralExperiment_withDifferentKeys() {
        ExperimentManager.setGeneralExperiment("keyA", "valueA")
        ExperimentManager.setGeneralExperiment("keyB", "valueB")

        assertEquals("valueA", ExperimentManager.getGeneralExperiment("keyA"))
        assertEquals("valueB", ExperimentManager.getGeneralExperiment("keyB"))
    }

    @Test
    fun getGeneralExperiment_returnsEmptyString_forUnknownKey() {
        assertEquals("", ExperimentManager.getGeneralExperiment("nonexistent"))
    }

    @Test
    fun clear_resetsAllValues() {
        ExperimentManager.setSessionId("session-1")
        ExperimentManager.setExperiment("exp-1")
        ExperimentManager.setSessionTag("tag-1")
        ExperimentManager.setGeneralExperiment("key", "val")

        ExperimentManager.clear()

        assertEquals("", ExperimentManager.getSessionId())
        assertEquals("", ExperimentManager.getExperiment())
        assertEquals("", ExperimentManager.getSessionTag())
        assertEquals("", ExperimentManager.getGeneralExperiment("key"))
    }
}
