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
            verboseLogging = get<NoctuaConfig>().noctua?.sandboxEnabled == true
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
            // Enable verbose logging only when sandbox mode is active (dev/QA).
            // Production builds (sandboxEnabled = false or absent) only emit errors.
            AppLogger.enabled = config.noctua?.sandboxEnabled == true
            // clientId is a credential (sent as X-CLIENT-ID) — never log its value
            AppLogger.d(Constants.NOCTUA_TAG, "NoctuaConfig loaded successfully")
            if (config.clientId.isNullOrBlank() || config.gameId == null) {
                AppLogger.e(Constants.NOCTUA_TAG, "NoctuaConfig is missing clientId/gameId — events will be unattributable")
            }
            config
        } catch (e: Exception) {
            AppLogger.e(Constants.NOCTUA_TAG, "Failed to load NoctuaConfig: ${e.message}")
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