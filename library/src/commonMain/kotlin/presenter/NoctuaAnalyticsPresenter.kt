package gg.noctua.analytics.presenter

import gg.noctua.analytics.data.remote.KtorRemoteNoctuaAnalytics
import gg.noctua.analytics.data.remote.RemoteNoctuaAnalytics
import gg.noctua.analytics.utils.AppLogger
import gg.noctua.analytics.utils.Constants
import gg.noctua.analytics.utils.DeviceUtils
import gg.noctua.analytics.data.remote.HttpClientFactory
import gg.noctua.analytics.utils.additionalParams
import gg.noctua.analytics.utils.mapToJsonString
import gg.noctua.analytics.utils.onError
import gg.noctua.analytics.utils.onSuccess
import kotlinx.coroutines.*

internal class NoctuaAnalyticsPresenter {

    private val httpClient = HttpClientFactory.create()
    private val deviceUtils = DeviceUtils()

    private val remote: RemoteNoctuaAnalytics by lazy {
        KtorRemoteNoctuaAnalytics(
            httpClient = httpClient,
            deviceUtils = deviceUtils
        )
    }

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun init() {

    }

    fun trackCustomEvent(eventName: String, properties: Map<String, Any>) {
        AppLogger.d("NoctuaAnalytics", "Sending Event")

        val eventPayload = mutableMapOf<String, Any>(
            "event_name" to eventName,
        )

        eventPayload.putAll(additionalParams(deviceUtils))
        eventPayload.putAll(properties)

        val propertiesToJson = mapToJsonString(eventPayload)

        scope.launch {
            val result = remote.sendEvents(listOf(propertiesToJson))
            result.onSuccess {
                AppLogger.d(Constants.NOCTUA_TAG, "Send Event $eventName Success")
            }
            result.onError { error ->
               AppLogger.e(Constants.NOCTUA_TAG, "Send Event $eventName Error : " + error.name)
            }
        }
    }
}
