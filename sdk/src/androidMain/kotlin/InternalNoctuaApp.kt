package com.noctuagames.labs.sdk

import android.app.Application
import com.noctuagames.labs.sdk.utils.AppContext

class InternalNoctuaApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AppContext.set(this)
    }
}