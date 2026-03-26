package com.noctuagames.labs.sdk.fakes

import com.noctuagames.labs.sdk.data.models.EventData
import com.noctuagames.labs.sdk.data.models.EventResponse
import com.noctuagames.labs.sdk.data.remote.RemoteNoctuaInternal
import com.noctuagames.labs.sdk.utils.DataError
import com.noctuagames.labs.sdk.utils.Result

internal class FakeRemoteNoctuaInternal : RemoteNoctuaInternal {
    var shouldSucceed = true
    var errorToReturn: DataError.Remote = DataError.Remote.UNKNOWN
    val capturedEvents = mutableListOf<List<String>>()

    override suspend fun sendEvents(events: List<String>): Result<EventResponse, DataError.Remote> {
        capturedEvents.add(events.toList())
        return if (shouldSucceed) {
            Result.Success(EventResponse(success = true, data = EventData(message = "ok")))
        } else {
            Result.Error(errorToReturn)
        }
    }

    fun reset() {
        shouldSucceed = true
        errorToReturn = DataError.Remote.UNKNOWN
        capturedEvents.clear()
    }
}
