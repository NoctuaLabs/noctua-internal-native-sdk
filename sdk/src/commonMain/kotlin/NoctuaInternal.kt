package gg.noctua.internal

import gg.noctua.internal.presenter.NoctuaInternalPresenter

object NoctuaInternal {

    private val presenter = NoctuaInternalPresenter()

    fun init() {
        presenter.init()
    }

    fun trackCustomEvent(eventName: String, properties: Map<String, Any>) {
        presenter.trackCustomEvent(eventName, properties)
    }
}