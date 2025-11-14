package com.noctuagames.labs.sdk.data.remote

import com.noctuagames.labs.sdk.utils.getCurrentTimeMillis
import com.noctuagames.labs.sdk.utils.isNetworkAvailable
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object NetworkStatusProvider {
    private var lastCheckTime = 0L
    private var lastResult = false
    private val lock = Mutex()

    /**
     * Cached check for network availability.
     * @param cacheDuration How long (in seconds) to reuse the last result before refreshing.
     */
    suspend fun isConnected(cacheDuration: Duration = 10.seconds): Boolean {
        val now = getCurrentTimeMillis()

        lock.withLock {
            if (now - lastCheckTime < cacheDuration.inWholeMilliseconds) {
                return lastResult
            }

            lastResult = try {
                isNetworkAvailable()
            } catch (e: Exception) {
                false
            }
            lastCheckTime = now
            return lastResult
        }
    }
}
