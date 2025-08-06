package com.noctuagames.labs.sdk

import com.noctuagames.labs.sdk.presenter.NoctuaInternalPresenter

object NoctuaInternal {

    private val presenter = NoctuaInternalPresenter()

    fun init() {
        presenter.init()
    }

    fun trackCustomEvent(eventName: String, properties: Map<String, Any>) {
        presenter.trackCustomEvent(eventName, properties)
    }
}