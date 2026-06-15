package com.noctuagames.labs.sdk.utils

import kotlin.test.Test
import kotlin.test.assertEquals

class SandboxResolverTest {

    @Test
    fun override_wins_over_config() {
        // host override is the source of truth, regardless of noctuagg.json
        assertEquals(true, resolveSandbox(override = true, config = false))
        assertEquals(false, resolveSandbox(override = false, config = true))
        assertEquals(true, resolveSandbox(override = true, config = null))
        assertEquals(false, resolveSandbox(override = false, config = null))
    }

    @Test
    fun fallsBackToConfig_whenNoOverride() {
        assertEquals(true, resolveSandbox(override = null, config = true))
        assertEquals(false, resolveSandbox(override = null, config = false))
    }

    @Test
    fun defaultsToFalse_whenBothAbsent() {
        assertEquals(false, resolveSandbox(override = null, config = null))
    }
}
