package com.noctuagames.labs.sdk.data.remote

import com.noctuagames.labs.sdk.data.models.EventResponse
import com.noctuagames.labs.sdk.utils.DataError
import com.noctuagames.labs.sdk.utils.Result

internal interface RemoteNoctuaInternal {
    suspend fun sendEvents(events: List<String>) : Result<EventResponse, DataError.Remote>
}