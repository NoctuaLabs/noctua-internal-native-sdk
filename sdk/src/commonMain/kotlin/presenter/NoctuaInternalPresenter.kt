package com.noctuagames.labs.sdk.presenter

import com.noctuagames.labs.sdk.data.database.dao.EventDao
import com.noctuagames.labs.sdk.data.database.entity.EventEntity
import com.noctuagames.labs.sdk.data.models.NoctuaConfig
import com.noctuagames.labs.sdk.data.remote.NetworkStatusProvider
import com.noctuagames.labs.sdk.data.remote.RemoteNoctuaInternal
import com.noctuagames.labs.sdk.utils.AppLogger
import com.noctuagames.labs.sdk.utils.Constants
import com.noctuagames.labs.sdk.utils.DeviceUtils
import com.noctuagames.labs.sdk.utils.ExperimentManager
import com.noctuagames.labs.sdk.utils.SessionTracker
import com.noctuagames.labs.sdk.utils.SessionTrackerConfig
import com.noctuagames.labs.sdk.utils.additionalParams
import com.noctuagames.labs.sdk.utils.mapToJsonString
import com.noctuagames.labs.sdk.utils.onError
import com.noctuagames.labs.sdk.utils.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class NoctuaInternalPresenter(
    private val deviceUtils: DeviceUtils,
    private val noctuaConfig: NoctuaConfig,
    private val remote: RemoteNoctuaInternal,
    private val eventDao: EventDao
) {
    private var sessionTracker: SessionTracker? = null
    private val globalExtraParams = mutableMapOf<String, Any>()

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {

        AppLogger.d(Constants.NOCTUA_TAG, "NoctuaConfig : ${noctuaConfig.clientId}")

        sessionTracker = SessionTracker(
            config = SessionTrackerConfig(),
            presenter = this
        )

        flushLocalEvents()

        AppLogger.d(Constants.NOCTUA_TAG, "Internal Noctua SDK is initialized")
    }

    fun setSessionTag(tag: String) {
        ExperimentManager.setSessionTag(tag)
    }

    fun getSessionTag() : String {
        return ExperimentManager.getSessionTag()
    }

    fun setExperiment(experiment: String) {
        ExperimentManager.setExperiment(experiment)
    }

    fun getExperiment() : String {
        return ExperimentManager.getExperiment()
    }

    fun setGeneralExperiment(experiment: String) {
        ExperimentManager.setGeneralExperiment(experiment, experiment)
    }

    fun getGeneralExperiment(experimentKey: String) : String {
        return ExperimentManager.getGeneralExperiment(experimentKey)
    }

    fun onInternalNoctuaApplicationPause(pauseStatus: Boolean) {
        if (sessionTracker == null) {
            AppLogger.i(Constants.NOCTUA_TAG, "SessionTracker is null")
            return
        }
        sessionTracker?.onApplicationPause(pauseStatus)
    }

    fun onInternalNoctuaDispose() {
        if (sessionTracker == null) {
            AppLogger.i(Constants.NOCTUA_TAG, "SessionTracker is null")
            return
        }

        sessionTracker?.dispose()
    }

    fun setSessionExtraParams(params: Map<String, Any>) {
        globalExtraParams.putAll(params)
    }

    private fun clearSessionExtraParams() {
        globalExtraParams.clear()
    }

    fun trackCustomEvent(
        eventName: String,
        properties: Map<String, Any>,
        revenue: Double? = null,
        currency: String? = null
    ) {
        AppLogger.d(Constants.NOCTUA_TAG, "Track Event $eventName")

        val eventPayload = mutableMapOf<String, Any>(
            "event_name" to eventName,
        )

        noctuaConfig.gameId?.let { gameId ->
            eventPayload["game_id"] = gameId
        }

        if (revenue != null && currency != null) {
            eventPayload["revenue"] = revenue
            eventPayload["currency"] = currency
        }

        val sessionTag = getSessionTag()
        val sessionEvents = setOf(
            "session_start",
            "session_end",
            "session_pause",
            "session_continue",
            "session_heartbeat"
        )

        if (sessionTag.isNotEmpty() && sessionEvents.contains(eventName)) {
            eventPayload["feature_tag"] = sessionTag

            if (globalExtraParams.isNotEmpty()) {
                eventPayload.putAll(globalExtraParams)
            }
        }

        val experiment = getExperiment()
        if (experiment.isNotEmpty()) {
            eventPayload["experiment"] = experiment
        }

        val sessionId = ExperimentManager.getSessionId()
        if (sessionId.isNotEmpty()) {
            eventPayload["session_id"] = sessionId
        }

        eventPayload.putAll(additionalParams(deviceUtils, noctuaConfig))
        eventPayload.putAll(properties)

        val propertiesToJson = mapToJsonString(eventPayload)

        scope.launch {
            val connected = NetworkStatusProvider.isConnected()
            if (!connected) {
                eventDao.insert(EventEntity(events = propertiesToJson))
                AppLogger.d(Constants.NOCTUA_TAG, "No internet connection, event $eventName saved locally")
                return@launch
            }

            val localEvents = eventDao.getAll()
            AppLogger.d(Constants.NOCTUA_TAG, "Local events count: ${localEvents.count()}")
            if (localEvents.count() > 100) {
                flushLocalEvents()
            } else {
                eventDao.insert(EventEntity(events = propertiesToJson))
            }
        }
    }

    private fun flushLocalEvents() {
        scope.launch {
            if (!NetworkStatusProvider.isConnected()) {
                AppLogger.d(Constants.NOCTUA_TAG, "No Internet Connection")
                return@launch
            }

            val events = eventDao.getAll()
            if (events.isEmpty()) {
                AppLogger.d(Constants.NOCTUA_TAG, "No local events")
                return@launch
            }

            val payloadList = events.map { it.events }
            AppLogger.i(Constants.NOCTUA_TAG, "Total events to send: ${payloadList.size}")

            val result = remote.sendEvents(payloadList)

            result.onSuccess {
                AppLogger.d(Constants.NOCTUA_TAG, "Local events successfully delivered")
                clearSessionExtraParams()
                eventDao.deleteAll()
            }

            result.onError { error ->
                clearSessionExtraParams()
                AppLogger.e(Constants.NOCTUA_TAG, "Local event delivery failed: ${error.name}")
            }
        }
    }
}

