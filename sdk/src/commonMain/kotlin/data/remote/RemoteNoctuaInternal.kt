package com.noctuagames.internal.sdk.data.remote

import com.noctuagames.internal.sdk.data.models.EventResponse
import com.noctuagames.internal.sdk.utils.DataError
import com.noctuagames.internal.sdk.utils.Result

internal interface RemoteNoctuaInternal {
    suspend fun sendEvents(events: List<String>) : Result<EventResponse, DataError.Remote>
}