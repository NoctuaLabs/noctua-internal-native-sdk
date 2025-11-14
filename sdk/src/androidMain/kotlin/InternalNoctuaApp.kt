package com.noctuagames.labs.sdk

import android.app.Application
import com.noctuagames.labs.sdk.di.initKoin
import com.noctuagames.labs.sdk.utils.AppContext
import org.koin.android.ext.koin.androidContext

class InternalNoctuaApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AppContext.set(this)
        initKoin {
            androidContext(this@InternalNoctuaApp)
        }
    }
}