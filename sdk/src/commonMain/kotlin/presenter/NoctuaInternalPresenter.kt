package com.noctuagames.labs.sdk.presenter

import com.noctuagames.labs.sdk.data.models.NoctuaConfig
import com.noctuagames.labs.sdk.data.remote.KtorRemoteNoctuaInternal
import com.noctuagames.labs.sdk.data.remote.RemoteNoctuaInternal
import com.noctuagames.labs.sdk.utils.AppLogger
import com.noctuagames.labs.sdk.utils.Constants
import com.noctuagames.labs.sdk.utils.DeviceUtils
import com.noctuagames.labs.sdk.data.remote.HttpClientFactory
import com.noctuagames.labs.sdk.utils.additionalParams
import com.noctuagames.labs.sdk.utils.loadAppConfig
import com.noctuagames.labs.sdk.utils.mapToJsonString
import com.noctuagames.labs.sdk.utils.onError
import com.noctuagames.labs.sdk.utils.onSuccess
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

    fun trackCustomEventWithRevenue(eventName: String, revenue: Double, currency:String , properties: Map<String, Any>) {

    }
}

