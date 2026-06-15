package com.noctuagames.sdk.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.noctuagames.labs.sdk.NoctuaInternal

/**
 * Toggles the sandbox override at runtime via [NoctuaInternal.setSandboxEnabled] and
 * proves it is winning: the readout shows the bundled `noctuagg.json` value (Config)
 * unchanged while the Override and Effective values follow the switch. When
 * `Effective != Config`, the override is demonstrably in effect.
 *
 * The Effective value is the same one stamped onto each event's `is_sandbox` field,
 * so flipping the switch then tracking an event proves it end-to-end.
 */
@Composable
fun SandboxSection(onLog: (String) -> Unit) {
    // Config is fixed (from noctuagg.json); override/effective change with the switch.
    val configValue = remember { NoctuaInternal.sandboxConfigValue() }
    var overrideValue by remember { mutableStateOf(NoctuaInternal.sandboxOverrideValue()) }
    var effective by remember { mutableStateOf(NoctuaInternal.isSandboxEnabled()) }

    Text("Sandbox", style = MaterialTheme.typography.titleMedium)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("sandboxEnabled override")
        Switch(
            checked = effective,
            onCheckedChange = { enabled ->
                NoctuaInternal.setSandboxEnabled(enabled)
                overrideValue = NoctuaInternal.sandboxOverrideValue()
                effective = NoctuaInternal.isSandboxEnabled()
                onLog("Sandbox override set to $enabled (config=$configValue, effective=$effective)")
            }
        )
    }

    Column {
        Text("Config (noctuagg.json): ${configValue ?: "absent"}", style = MaterialTheme.typography.bodySmall)
        Text("Override: ${overrideValue ?: "none"}", style = MaterialTheme.typography.bodySmall)
        Text(
            "Effective (is_sandbox): $effective" +
                if (configValue != null && effective != configValue) "  ← override winning" else "",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
