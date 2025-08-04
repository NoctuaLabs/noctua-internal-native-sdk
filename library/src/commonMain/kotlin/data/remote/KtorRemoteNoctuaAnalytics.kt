package gg.noctua.analytics.data.remote

import gg.noctua.analytics.data.models.EventResponse
import gg.noctua.analytics.utils.Constants
import gg.noctua.analytics.utils.DataError
import gg.noctua.analytics.utils.DeviceUtils
import gg.noctua.analytics.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType

internal class KtorRemoteNoctuaAnalytics (
    private val httpClient: HttpClient,
    private val deviceUtils: DeviceUtils
): RemoteNoctuaAnalytics {

    override suspend fun sendEvents(events: List<String>): Result<EventResponse, DataError.Remote> {

        val ndjsonBody = events.joinToString(separator = "\n")

        return safeCall<EventResponse> {
            httpClient.post(
                urlString = "${Constants.BASE_URL}/events",
            ) {
                method = HttpMethod.Post

                contentType(ContentType.parse("application/x-ndjson"))

                header("Accept-Language", deviceUtils.acceptLanguage)
                header("X-CLIENT-ID", Constants.CLIENT_ID)
                header("X-BUNDLE-ID", deviceUtils.bundleId)
                header("X-LANGUAGE", deviceUtils.language)
                header("X-COUNTRY", deviceUtils.country)
                header("X-CURRENCY", deviceUtils.currency)
                header("X-DEVICE-ID", deviceUtils.deviceId)
                header("X-PLATFORM", "direct") //example playstore, appstore, direct
                header("X-OS", deviceUtils.platform)
                header("X-OS-AGENT", deviceUtils.osAgent)
                header("X-SDK-VERSION", "1.0.0")

                setBody(ndjsonBody)
            }
        }
    }
}