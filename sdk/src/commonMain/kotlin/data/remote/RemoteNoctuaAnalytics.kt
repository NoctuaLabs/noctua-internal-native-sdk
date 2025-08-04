package gg.noctua.analytics.data.remote

import gg.noctua.analytics.data.models.EventResponse
import gg.noctua.analytics.utils.DataError
import gg.noctua.analytics.utils.Result

internal interface RemoteNoctuaAnalytics {
    suspend fun sendEvents(events: List<String>) : Result<EventResponse, DataError.Remote>
}