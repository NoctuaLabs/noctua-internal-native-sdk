package com.noctuagames.sdk.example

import android.app.Application
import com.noctuagames.labs.sdk.di.initKoin
import com.noctuagames.labs.sdk.utils.AppContext
import org.koin.android.ext.koin.androidContext

/**
 * Android Application subclass that initializes the Noctua SDK via Koin.
 *
 * AppContext must be set before initKoin so that the SDK can load noctuagg.json
 * from the app's assets during dependency injection.
 */
class ExampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContext.set(this)
        initKoin {
            androidContext(this@ExampleApp)
        }
    }
}
