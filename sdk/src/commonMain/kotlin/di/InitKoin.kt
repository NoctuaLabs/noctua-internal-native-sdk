package com.noctuagames.labs.sdk.di

import com.noctuagames.labs.sdk.utils.SandboxState
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * @param sandboxEnabled optional host override for `sandboxEnabled` that wins over
 *   `noctuagg.json`. Null (the default) falls back to the bundled config value. This is
 *   sugar over [com.noctuagames.labs.sdk.NoctuaInternal.setSandboxEnabled] — hosts that
 *   manage their own init can call that public function instead.
 * @param config optional Koin declaration (e.g. to register the Android context).
 */
fun initKoin(sandboxEnabled: Boolean? = null, config: KoinAppDeclaration? = null) {
    // Set the override before startKoin so the NoctuaConfig/HttpClient singles resolve it.
    if (sandboxEnabled != null) SandboxState.setOverride(sandboxEnabled)
    startKoin {
        config?.invoke(this)
        modules(sharedModule, platformModule)
    }
}