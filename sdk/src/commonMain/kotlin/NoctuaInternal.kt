package com.noctuagames.labs.sdk

import com.noctuagames.labs.sdk.presenter.NoctuaInternalPresenter
import com.noctuagames.labs.sdk.utils.AppLogger
import com.noctuagames.labs.sdk.utils.Constants
import com.noctuagames.labs.sdk.utils.disposePlatformLifecycle
import org.koin.mp.KoinPlatform.getKoin

object NoctuaInternal {

    // Resolved per call instead of in the object initializer: a static-init
    // failure (Koin not started yet) would otherwise poison the class for the
    // rest of the process. The presenter is a Koin single, so this is cheap.
    private val presenter: NoctuaInternalPresenter?
        get() = try {
            getKoin().get<NoctuaInternalPresenter>()
        } catch (e: Exception) {
            AppLogger.e(
                Constants.NOCTUA_TAG,
                "NoctuaInternal called before initialization — call initKoin()/initKoinManually() first (${e.message})"
            )
            null
        }

    fun saveExternalEvents(jsonString: String) {
        presenter?.saveExternalEvents(jsonString)
    }

    fun getExternalEvents(onResult: (List<String>) -> Unit) {
        presenter?.getExternalEvents(onResult) ?: onResult(emptyList())
    }

    fun deleteExternalEvents() {
        presenter?.deleteExternalEvents()
    }

    // NEW: Per-row event storage for unlimited event tracking

    fun insertExternalEvent(eventJson: String) {
        presenter?.insertExternalEvent(eventJson)
    }

    fun getExternalEventsBatch(limit: Int, offset: Int, callback: (String) -> Unit) {
        presenter?.getExternalEventsBatch(limit, offset, callback) ?: callback("[]")
    }

    fun deleteExternalEventsByIds(idsJson: String, callback: (Int) -> Unit) {
        presenter?.deleteExternalEventsByIds(idsJson, callback) ?: callback(0)
    }

    fun getExternalEventCount(callback: (Int) -> Unit) {
        presenter?.getExternalEventCount(callback) ?: callback(0)
    }

    fun onInternalNoctuaApplicationPause(pauseStatus: Boolean) {
        presenter?.onInternalNoctuaApplicationPause(pauseStatus)
    }

    fun onInternalNoctuaDispose() {
        // Stop platform lifecycle callbacks first so no pause/resume fires into
        // a presenter that's about to cancel its scope.
        disposePlatformLifecycle()
        presenter?.onInternalNoctuaDispose()
    }

    fun setSessionTag(tag: String) {
        presenter?.setSessionTag(tag)
    }

    fun getSessionTag() : String {
        return presenter?.getSessionTag() ?: ""
    }

    fun setExperiment(experiment: String) {
        presenter?.setExperiment(experiment)
    }

    fun getExperiment() : String {
        return presenter?.getExperiment() ?: ""
    }

    @Deprecated(
        message = "Stores the experiment name as both key and value. Use setGeneralExperiment(key, value) instead.",
        replaceWith = ReplaceWith("setGeneralExperiment(experiment, experiment)")
    )
    fun setGeneralExperiment(experiment: String) {
        presenter?.setGeneralExperiment(experiment, experiment)
    }

    fun setGeneralExperiment(key: String, value: String) {
        presenter?.setGeneralExperiment(key, value)
    }

    fun getGeneralExperiment(experimentKey: String) : String {
        return presenter?.getGeneralExperiment(experimentKey) ?: ""
    }

    fun trackCustomEvent(eventName: String, properties: Map<String, Any>) {
        presenter?.trackCustomEvent(eventName, properties)
    }

    fun trackCustomEventWithRevenue(eventName: String, revenue: Double, currency: String, properties: Map<String, Any>) {
        presenter?.trackCustomEventWithRevenue(eventName, properties, revenue, currency)
    }

    fun setSessionExtraParams(params: Map<String, Any>) {
        presenter?.setSessionExtraParams(params)
    }
}
