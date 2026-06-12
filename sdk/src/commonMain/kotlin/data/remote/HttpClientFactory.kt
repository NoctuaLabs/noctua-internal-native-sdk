package com.noctuagames.labs.sdk.data.remote

import com.noctuagames.labs.sdk.utils.AppLogger
import com.noctuagames.labs.sdk.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object HttpClientFactory {

    fun create(engine: HttpClientEngine, verboseLogging: Boolean = false): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }
            // Only installed in sandbox builds. Ktor's Logging plugin buffers the
            // ENTIRE request body into a StringBuilder before invoking the logger —
            // even when the logger discards the output — so at LogLevel.ALL/BODY a
            // large event batch duplicates itself in memory and OOMs low-end devices.
            // HEADERS never touches the body; credential headers are redacted.
            if (verboseLogging) {
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            AppLogger.d(Constants.NOCTUA_TAG, message)
                        }
                    }
                    level = LogLevel.HEADERS
                    sanitizeHeader { name -> name == "X-CLIENT-ID" || name == "X-DEVICE-ID" }
                }
            }
        }
    }
}
