package com.noctuagames.labs.sdk

import com.noctuagames.labs.sdk.presenter.NoctuaInternalPresenter

object NoctuaInternal {

    private val presenter = NoctuaInternalPresenter()

    fun onInternalNoctuaApplicationPause(pauseStatus: Boolean) {
        presenter.onInternalNoctuaApplicationPause(pauseStatus)
    }

    fun onInternalNoctuaDispose() {
        presenter.onInternalNoctuaDispose()
    }

    fun trackCustomEvent(eventName: String, properties: Map<String, Any>) {
        presenter.trackCustomEvent(eventName, properties)
    }

    fun trackCustomEventWithRevenue(eventName: String, revenue: Double, currency: String, properties: Map<String, Any>) {
        presenter.trackCustomEventWithRevenue(eventName, revenue, currency, properties)
    }
}