package gg.noctua.internal.data.remote

import gg.noctua.internal.data.models.EventResponse
import gg.noctua.internal.utils.DataError
import gg.noctua.internal.utils.Result

internal interface RemoteNoctuaInternal {
    suspend fun sendEvents(events: List<String>) : Result<EventResponse, DataError.Remote>
}