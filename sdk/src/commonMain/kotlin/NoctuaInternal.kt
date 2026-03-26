package com.noctuagames.labs.sdk

import com.noctuagames.labs.sdk.presenter.NoctuaInternalPresenter
import org.koin.mp.KoinPlatform.getKoin

object NoctuaInternal {

    private val presenter = getKoin().get<NoctuaInternalPresenter>()

    fun saveExternalEvents(jsonString: String) {
        presenter.saveExternalEvents(jsonString)
    }

    fun getExternalEvents(onResult: (List<String>) -> Unit) {
        presenter.getExternalEvents(onResult)
    }

    fun deleteExternalEvents() {
        presenter.deleteExternalEvents()
    }

    // NEW: Per-row event storage for unlimited event tracking

    fun insertExternalEvent(eventJson: String) {
        presenter.insertExternalEvent(eventJson)
    }

    fun getExternalEventsBatch(limit: Int, offset: Int, callback: (String) -> Unit) {
        presenter.getExternalEventsBatch(limit, offset, callback)
    }

    fun deleteExternalEventsByIds(idsJson: String, callback: (Int) -> Unit) {
        presenter.deleteExternalEventsByIds(idsJson, callback)
    }

    fun getExternalEventCount(callback: (Int) -> Unit) {
        presenter.getExternalEventCount(callback)
    }

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
        presenter.trackCustomEventWithRevenue(eventName, properties, revenue, currency)
    }

    fun setSessionExtraParams(params: Map<String, Any>) {
        presenter.setSessionExtraParams(params)
    }
}