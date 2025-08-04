package gg.noctua.internal.presenter

import gg.noctua.internal.data.models.NoctuaConfig
import gg.noctua.internal.data.remote.KtorRemoteNoctuaInternal
import gg.noctua.internal.data.remote.RemoteNoctuaInternal
import gg.noctua.internal.utils.AppLogger
import gg.noctua.internal.utils.Constants
import gg.noctua.internal.utils.DeviceUtils
import gg.noctua.internal.data.remote.HttpClientFactory
import gg.noctua.internal.utils.additionalParams
import gg.noctua.internal.utils.loadAppConfig
import gg.noctua.internal.utils.mapToJsonString
import gg.noctua.internal.utils.onError
import gg.noctua.internal.utils.onSuccess
import kotlinx.coroutines.*

internal class NoctuaInternalPresenter {

    private val httpClient = HttpClientFactory.create()
    private val deviceUtils = DeviceUtils()

    private var noctuaConfig: NoctuaConfig
    private var remote: RemoteNoctuaInternal

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        noctuaConfig = runBlocking {
            try {
                val config = loadAppConfig()
                AppLogger.d(Constants.NOCTUA_TAG, "NoctuaConfig Loaded: ${config.clientId}")
                config
            } catch (e: Exception) {
                AppLogger.e(Constants.NOCTUA_TAG, "Failed to load NoctuaConfig: ${e.message}")
                NoctuaConfig(
                    clientId = Constants.CLIENT_ID,
                    productCode = null,
                    adjust = null,
                    facebook = null,
                    firebase = null,
                    noctua = null,
                )
            }
        }

        AppLogger.d(Constants.NOCTUA_TAG, "NoctuaConfig : ${noctuaConfig.clientId}")

        remote = KtorRemoteNoctuaInternal(
            httpClient = httpClient,
            deviceUtils = deviceUtils,
            noctuaConfig = noctuaConfig
        )

        AppLogger.d(Constants.NOCTUA_TAG, "Network Client Created")
        AppLogger.d(Constants.NOCTUA_TAG, "SDK is initialized")
    }

    fun init() {
        AppLogger.d(Constants.NOCTUA_TAG, "Init SDK")
    }

    fun trackCustomEvent(eventName: String, properties: Map<String, Any>) {
        AppLogger.d(Constants.NOCTUA_TAG, "Sending Event $eventName")

        val eventPayload = mutableMapOf<String, Any>(
            "event_name" to eventName,
        )

        eventPayload.putAll(additionalParams(deviceUtils, noctuaConfig))
        eventPayload.putAll(properties)

        val propertiesToJson = mapToJsonString(eventPayload)

        scope.launch {
            val result = remote.sendEvents(listOf(propertiesToJson))
            result.onSuccess {
                AppLogger.d(Constants.NOCTUA_TAG, "Send Event $eventName Success")
            }
            result.onError { error ->
                AppLogger.e(Constants.NOCTUA_TAG, "Send Event $eventName Error : ${error.name}")
            }
        }
    }
}

