package com.noctuagames.labs.sdk.presenter

import com.noctuagames.labs.sdk.data.database.dao.EventDao
import com.noctuagames.labs.sdk.data.database.entity.EventEntity
import com.noctuagames.labs.sdk.data.local.dao.ExternalEventDao
import com.noctuagames.labs.sdk.data.local.entity.ExternalEventEntity
import com.noctuagames.labs.sdk.data.models.NoctuaConfig
import com.noctuagames.labs.sdk.data.remote.NetworkStatusProvider
import com.noctuagames.labs.sdk.data.remote.RemoteNoctuaInternal
import com.noctuagames.labs.sdk.utils.AppLogger
import com.noctuagames.labs.sdk.utils.Constants
import com.noctuagames.labs.sdk.utils.DeviceUtils
import com.noctuagames.labs.sdk.utils.ExperimentManager
import com.noctuagames.labs.sdk.utils.Result
import com.noctuagames.labs.sdk.utils.SessionEventSink
import com.noctuagames.labs.sdk.utils.SessionTracker
import com.noctuagames.labs.sdk.utils.SessionTrackerConfig
import com.noctuagames.labs.sdk.utils.additionalParams
import com.noctuagames.labs.sdk.utils.mapToJsonString
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.put
import kotlin.concurrent.Volatile

@OptIn(ExperimentalCoroutinesApi::class)
internal class NoctuaInternalPresenter(
    private val deviceUtils: DeviceUtils,
    private val noctuaConfig: NoctuaConfig,
    private val remote: RemoteNoctuaInternal,
    private val eventDao: EventDao,
    private val externalEventDao: ExternalEventDao,
    // Single-threaded so DB read-modify-write sequences never run in parallel.
    dispatcher: CoroutineDispatcher = Dispatchers.IO.limitedParallelism(1),
    private val isConnected: suspend () -> Boolean = { NetworkStatusProvider.isConnected() }
) : SessionEventSink {
    private var sessionTracker: SessionTracker? = null

    @Volatile
    private var globalExtraParams: Map<String, Any> = emptyMap()

    private val scope = CoroutineScope(dispatcher + SupervisorJob())
    private val flushMutex = Mutex()

    // Master switch for the native internal tracker. When false (or absent) the
    // SDK runs no session tracking, emits no noctua_user_engagement, stores no
    // custom events, and uploads nothing — the consumer's own tracker is expected
    // to handle analytics in that case. The external event store is unaffected.
    private val trackerEnabled = noctuaConfig.noctua?.nativeInternalTrackerEnabled == true

    init {
        if (trackerEnabled) {
            sessionTracker = SessionTracker(
                config = SessionTrackerConfig(),
                eventSink = this,
                userEngagementEnabled = true
            )

            // Auto-start the first session so heartbeat fires on all platforms
            // (especially iOS where no lifecycle observer existed before).
            // The guard in SessionTracker.onApplicationPause prevents duplicate calls.
            sessionTracker?.onApplicationPause(false)

            flushLocalEvents()

            AppLogger.d(Constants.NOCTUA_TAG, "Internal Noctua SDK is initialized")
        } else {
            AppLogger.i(
                Constants.NOCTUA_TAG,
                "Native internal tracker disabled (nativeInternalTrackerEnabled is not true); " +
                    "skipping session tracking and event upload"
            )
        }
    }

    fun saveExternalEvents(jsonString: String) {
        scope.launch {
            runCatchingNonFatal("saveExternalEvents") {
                externalEventDao.insert(ExternalEventEntity(eventJson = jsonString))
            }
        }
    }

    fun getExternalEvents(onResult: (List<String>) -> Unit) {
        scope.launch {
            val eventList = runCatchingNonFatal("getExternalEvents") {
                externalEventDao.getAll().map { it.eventJson }
            } ?: emptyList()
            onResult(eventList)
        }
    }

    fun deleteExternalEvents() {
        scope.launch {
            runCatchingNonFatal("deleteExternalEvents") {
                externalEventDao.deleteAll()
            }
        }
    }

    // NEW: Per-row event storage methods for unlimited event tracking

    fun insertExternalEvent(eventJson: String) {
        scope.launch {
            runCatchingNonFatal("insertExternalEvent") {
                externalEventDao.insertSingle(ExternalEventEntity(eventJson = eventJson))
            }
        }
    }

    fun getExternalEventsBatch(limit: Int, offset: Int, callback: (String) -> Unit) {
        scope.launch {
            val payload = runCatchingNonFatal("getExternalEventsBatch") {
                val events = externalEventDao.getBatch(limit, offset)
                // JSON array of {id, eventJson, createdAt}. eventJson is embedded as a
                // JSON element when it parses, otherwise as an escaped string — manual
                // interpolation would produce broken JSON for any value containing quotes.
                buildJsonArray {
                    events.forEach { e ->
                        addJsonObject {
                            put("id", e.id)
                            put(
                                "eventJson",
                                try {
                                    Json.parseToJsonElement(e.eventJson)
                                } catch (_: Exception) {
                                    JsonPrimitive(e.eventJson)
                                }
                            )
                            put("createdAt", e.createdAt)
                        }
                    }
                }.toString()
            } ?: "[]"
            callback(payload)
        }
    }

    fun deleteExternalEventsByIds(idsJson: String, callback: (Int) -> Unit) {
        scope.launch {
            val deletedCount = runCatchingNonFatal("deleteExternalEventsByIds") {
                // idsJson = "[1,2,3]" — non-numeric tokens are skipped instead of crashing
                val ids = idsJson.removeSurrounding("[", "]")
                    .split(",")
                    .mapNotNull { it.trim().toLongOrNull() }
                if (ids.isEmpty()) 0 else externalEventDao.deleteByIds(ids)
            } ?: 0
            callback(deletedCount)
        }
    }

    fun getExternalEventCount(callback: (Int) -> Unit) {
        scope.launch {
            val count = runCatchingNonFatal("getExternalEventCount") {
                externalEventDao.getCount()
            } ?: 0
            callback(count)
        }
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

    fun setGeneralExperiment(key: String, value: String) {
        ExperimentManager.setGeneralExperiment(key, value)
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
        // scope + remote always exist (even when the tracker is disabled and
        // sessionTracker is null), so always release them to avoid a leak.
        sessionTracker?.dispose()
        scope.cancel()
        // Release the HTTP client's thread pool / connections.
        runCatchingNonFatal("remote.close") { remote.close() }
    }

    fun setSessionExtraParams(params: Map<String, Any>) {
        globalExtraParams = globalExtraParams + params
    }

    private fun consumeSessionExtraParams(): Map<String, Any> {
        val snapshot = globalExtraParams
        globalExtraParams = emptyMap()
        return snapshot
    }

    /**
     * Implements [SessionEventSink.trackCustomEvent] for session lifecycle events.
     * Also serves as the general event tracking method with optional revenue parameters.
     */
    override fun trackCustomEvent(
        eventName: String,
        properties: Map<String, Any>,
    ) {
        trackCustomEventInternal(eventName, properties)
    }

    fun trackCustomEventWithRevenue(
        eventName: String,
        properties: Map<String, Any>,
        revenue: Double,
        currency: String
    ) {
        trackCustomEventInternal(eventName, properties, revenue, currency)
    }

    private fun trackCustomEventInternal(
        eventName: String,
        properties: Map<String, Any>,
        revenue: Double? = null,
        currency: String? = null
    ) {
        if (!trackerEnabled) {
            AppLogger.d(
                Constants.NOCTUA_TAG,
                "Native internal tracker disabled, skipping event $eventName"
            )
            return
        }

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
            "session_heartbeat",
            "noctua_user_engagement"
        )

        // Snapshot-and-clear synchronously so a concurrent event can't observe
        // params meant for this one, and the payload below is built from an
        // immutable copy.
        val extraParams = consumeSessionExtraParams()

        if (sessionTag.isNotEmpty() && sessionEvents.contains(eventName)) {
            eventPayload["feature_tag"] = sessionTag

            if (extraParams.isNotEmpty()) {
                eventPayload.putAll(extraParams)
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
            runCatchingNonFatal("trackCustomEvent") {
                eventDao.insert(EventEntity(events = propertiesToJson))

                val evicted = eventDao.trimToSize(MAX_STORED_EVENTS)
                if (evicted > 0) {
                    AppLogger.e(Constants.NOCTUA_TAG, "Event store full, evicted $evicted oldest events")
                }

                if (!isConnected()) {
                    AppLogger.d(Constants.NOCTUA_TAG, "No internet connection, event $eventName saved locally")
                    return@runCatchingNonFatal
                }

                val pendingCount = eventDao.getCount()
                AppLogger.d(Constants.NOCTUA_TAG, "Local events count: $pendingCount")

                if (pendingCount > FLUSH_THRESHOLD) {
                    flushLocalEvents()
                }
            }
        }
    }

    internal fun flushLocalEvents() {
        if (!trackerEnabled) return
        scope.launch {
            runCatchingNonFatal("flushLocalEvents") {
                flushMutex.withLock {
                    if (!isConnected()) {
                        AppLogger.d(Constants.NOCTUA_TAG, "No Internet Connection")
                        return@withLock
                    }

                    // Bounded batches, deleted by ID only after a confirmed send —
                    // events inserted while an upload is in flight are never touched,
                    // and one oversized backlog can't produce a single giant payload.
                    while (true) {
                        val batch = eventDao.getBatch(FLUSH_BATCH_SIZE)
                        if (batch.isEmpty()) {
                            AppLogger.d(Constants.NOCTUA_TAG, "No local events")
                            break
                        }

                        AppLogger.i(Constants.NOCTUA_TAG, "Sending batch of ${batch.size} events")

                        when (val result = remote.sendEvents(batch.map { it.events })) {
                            is Result.Success -> {
                                eventDao.deleteByIds(batch.map { it.id })
                                AppLogger.d(Constants.NOCTUA_TAG, "Batch of ${batch.size} events delivered")
                            }
                            is Result.Error -> {
                                AppLogger.e(Constants.NOCTUA_TAG, "Local event delivery failed: ${result.error.name}")
                                break
                            }
                        }
                    }
                }
            }
        }
    }

    // Logs instead of letting the SupervisorJob swallow the failure silently;
    // callers fall back to a safe default so consumer callbacks always fire.
    private inline fun <T> runCatchingNonFatal(operation: String, block: () -> T): T? {
        return try {
            block()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppLogger.e(Constants.NOCTUA_TAG, "$operation failed: ${e.message}")
            null
        }
    }

    private companion object {
        const val FLUSH_THRESHOLD = 100
        const val FLUSH_BATCH_SIZE = 100
        const val MAX_STORED_EVENTS = 2000
    }
}
