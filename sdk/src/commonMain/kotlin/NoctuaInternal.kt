package com.noctuagames.labs.sdk

import com.noctuagames.labs.sdk.presenter.NoctuaInternalPresenter
import org.koin.mp.KoinPlatform.getKoin

object NoctuaInternal {

    private val presenter = getKoin().get<NoctuaInternalPresenter>()

    fun onInternalNoctuaApplicationPause(pauseStatus: Boolean) {
        presenter.onInternalNoctuaApplicationPause(pauseStatus)
    }

    fun onInternalNoctuaDispose() {
        presenter.onInternalNoctuaDispose()
    }

    fun setSessionTag(tag: String) {
        presenter.setSessionTag(tag)
    }

    fun getSessionTag() : String {
        return presenter.getSessionTag()
    }

    fun setExperiment(experiment: String) {
        presenter.setExperiment(experiment)
    }

    fun getExperiment() : String {
        return presenter.getExperiment()
    }

    fun setGeneralExperiment(experiment: String) {
        presenter.setGeneralExperiment(experiment)
    }

    fun getGeneralExperiment(experimentKey: String) : String {
        return presenter.getGeneralExperiment(experimentKey)
    }

    fun trackCustomEvent(eventName: String, properties: Map<String, Any>) {
        presenter.trackCustomEvent(eventName, properties)
    }

    fun trackCustomEventWithRevenue(eventName: String, revenue: Double, currency: String, properties: Map<String, Any>) {
        presenter.trackCustomEvent(eventName, properties, revenue, currency)
    }

    fun setSessionExtraParams(params: Map<String, Any>) {
        presenter.setSessionExtraParams(params)
    }
}