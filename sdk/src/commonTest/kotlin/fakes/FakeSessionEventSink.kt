package com.noctuagames.labs.sdk.fakes

import com.noctuagames.labs.sdk.utils.SessionEventSink

internal class FakeSessionEventSink : SessionEventSink {

    data class TrackedEvent(
        val eventName: String,
        val properties: Map<String, Any>
    )

    val trackedEvents = mutableListOf<TrackedEvent>()

    override fun trackCustomEvent(eventName: String, properties: Map<String, Any>) {
        trackedEvents.add(TrackedEvent(eventName, properties.toMap()))
    }

    fun eventNames(): List<String> = trackedEvents.map { it.eventName }

    fun hasEvent(name: String): Boolean = trackedEvents.any { it.eventName == name }

    fun countOf(name: String): Int = trackedEvents.count { it.eventName == name }

    fun reset() {
        trackedEvents.clear()
    }
}
