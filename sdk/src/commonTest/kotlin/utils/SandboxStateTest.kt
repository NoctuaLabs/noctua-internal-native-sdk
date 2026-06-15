package com.noctuagames.labs.sdk.utils

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SandboxStateTest {

    @BeforeTest
    fun setup() = SandboxState.reset()

    @AfterTest
    fun teardown() = SandboxState.reset()

    @Test
    fun noOverride_fallsBackToConfig() {
        assertEquals(null, SandboxState.overrideOrNull())
        assertEquals(true, SandboxState.resolve(true))
        assertEquals(false, SandboxState.resolve(false))
        assertEquals(false, SandboxState.resolve(null))
    }

    @Test
    fun setOverrideTrue_winsOverConfig() {
        SandboxState.setOverride(true)
        assertEquals(true, SandboxState.overrideOrNull())
        assertEquals(true, SandboxState.resolve(false))
        assertEquals(true, SandboxState.resolve(null))
    }

    @Test
    fun setOverrideFalse_winsOverConfigTrue() {
        SandboxState.setOverride(false)
        assertEquals(false, SandboxState.resolve(true))
    }

    @Test
    fun setOverride_togglesAppLogger() {
        SandboxState.setOverride(true)
        assertEquals(true, AppLogger.enabled)
        SandboxState.setOverride(false)
        assertEquals(false, AppLogger.enabled)
    }
}
