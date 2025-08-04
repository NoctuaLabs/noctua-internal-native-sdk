package gg.noctua.analytics

import gg.noctua.analytics.presenter.NoctuaAnalyticsPresenter

object NoctuaAnalytics {

    private val presenter = NoctuaAnalyticsPresenter()

    fun init() {
        presenter.init()
    }

    fun trackCustomEvent(eventName: String, properties: Map<String, Any>) {
        presenter.trackCustomEvent(eventName, properties)
    }
}