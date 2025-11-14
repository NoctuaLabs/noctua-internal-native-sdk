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

    single { HttpClientFactory.create(get()) }

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single { get<NoctuaDatabase>().eventDao }

    single { DeviceUtils() }

    single<NoctuaConfig> {
        try {
            val config = loadAppConfig()
            AppLogger.d(Constants.NOCTUA_TAG, "NoctuaConfig Loaded: ${config.clientId}")
            config
        } catch (e: Exception) {
            AppLogger.e(Constants.NOCTUA_TAG, "Failed to load NoctuaConfig: ${e.message}")
            NoctuaConfig(null, null, null, null, null, null)
        }
    }

    single<RemoteNoctuaInternal> {
        KtorRemoteNoctuaInternal(
            httpClient = get(),
            deviceUtils = get(),
            noctuaConfig = get()
        )
    }

    factory { NoctuaInternalPresenter(get(), get(), get(), get()) }

}