package gg.noctua.internal.data.remote

import gg.noctua.internal.data.models.EventResponse
import gg.noctua.internal.data.models.NoctuaConfig
import gg.noctua.internal.utils.Constants
import gg.noctua.internal.utils.DataError
import gg.noctua.internal.utils.DeviceUtils
import gg.noctua.internal.utils.Result
import gg.noctua.internal.utils.getPlatformType
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType

internal class KtorRemoteNoctuaInternal (
    private val httpClient: HttpClient,
    private val deviceUtils: DeviceUtils,
    private val noctuaConfig: NoctuaConfig
): RemoteNoctuaInternal {

    override suspend fun sendEvents(events: List<String>): Result<EventResponse, DataError.Remote> {

        val ndjsonBody = events.joinToString(separator = "\n")

        return safeCall<EventResponse> {
            httpClient.post(
                urlString = "${Constants.BASE_URL}/events",
            ) {
                method = HttpMethod.Post

                contentType(ContentType.parse("application/x-ndjson"))

                header("Accept-Language", deviceUtils.acceptLanguage)
                header("X-CLIENT-ID", noctuaConfig.clientId)
                header("X-BUNDLE-ID", deviceUtils.bundleId)
                header("X-LANGUAGE", deviceUtils.language)
                header("X-COUNTRY", deviceUtils.country)
                header("X-CURRENCY", deviceUtils.currency)
                header("X-DEVICE-ID", deviceUtils.deviceId)
                header("X-PLATFORM", getPlatformType())
                header("X-OS", deviceUtils.platform)
                header("X-OS-AGENT", deviceUtils.osAgent)
                header("X-SDK-VERSION", "0.1.0")

                setBody(ndjsonBody)
            }
        }
    }
}