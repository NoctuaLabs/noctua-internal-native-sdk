package com.noctuagames.labs.sdk.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.noctuagames.labs.sdk.data.database.DatabaseFactory
import com.noctuagames.labs.sdk.data.database.NoctuaDatabase
import com.noctuagames.labs.sdk.data.models.NoctuaConfig
import com.noctuagames.labs.sdk.data.remote.HttpClientFactory
import com.noctuagames.labs.sdk.data.remote.KtorRemoteNoctuaInternal
import com.noctuagames.labs.sdk.data.remote.RemoteNoctuaInternal
import com.noctuagames.labs.sdk.presenter.NoctuaInternalPresenter
import com.noctuagames.labs.sdk.utils.AppLogger
import com.noctuagames.labs.sdk.utils.Constants
import com.noctuagames.labs.sdk.utils.DeviceUtils
import com.noctuagames.labs.sdk.utils.SandboxState
import com.noctuagames.labs.sdk.utils.loadAppConfig
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    single {
        HttpClientFactory.create(
            engine = get(),
            // Resolving the config here also guarantees AppLogger.enabled is set
            // before the client is built. Logging is sandbox-only — see HttpClientFactory.
            // SandboxState applies a host override (if any) over the config value.
            verboseLogging = SandboxState.resolve(get<NoctuaConfig>().noctua?.sandboxEnabled)
        )
    }

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single { get<NoctuaDatabase>().eventDao }
    single { get<NoctuaDatabase>().externalEventDao }

    single { DeviceUtils() }

    single<NoctuaConfig> {
        try {
            val config = loadAppConfig()
            // A host override (SandboxState) wins over noctuagg.json. Enable verbose
            // logging only when sandbox mode is active (dev/QA); production builds
            // (sandboxEnabled false/absent and no override) only emit errors.
            val effectiveSandbox = SandboxState.resolve(config.noctua?.sandboxEnabled)
            AppLogger.enabled = effectiveSandbox
            // clientId is a credential (sent as X-CLIENT-ID) — never log its value
            AppLogger.d(
                Constants.NOCTUA_TAG,
                "NoctuaConfig loaded successfully (sandboxEnabled=$effectiveSandbox, override=${SandboxState.overrideOrNull() ?: "none"})"
            )
            if (config.clientId.isNullOrBlank() || config.gameId == null) {
                AppLogger.e(Constants.NOCTUA_TAG, "NoctuaConfig is missing clientId/gameId — events will be unattributable")
            }
            config
        } catch (e: Exception) {
            AppLogger.e(Constants.NOCTUA_TAG, "Failed to load NoctuaConfig: ${e.message}")
            // Honor a host override even when the config file fails to load.
            AppLogger.enabled = SandboxState.resolve(null)
            NoctuaConfig()
        }
    }

    single<RemoteNoctuaInternal> {
        KtorRemoteNoctuaInternal(
            httpClient = get(),
            deviceUtils = get(),
            noctuaConfig = get()
        )
    }

    // single, not factory: the presenter owns the CoroutineScope, SessionTracker,
    // and session state — a second instance would run a disconnected session.
    single {
        NoctuaInternalPresenter(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

}