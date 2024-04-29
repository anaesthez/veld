package com.nesterov.veld.di.configuration.client

import com.nesterov.veld.di.configuration.HttpEngineFactory
import com.nesterov.veld.network.dnd.config.HttpClientConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientConfigImpl: HttpClientConfig {
    private val httpEngine: HttpClientEngineFactory<HttpClientEngineConfig> by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        HttpEngineFactory().createEngine()
    }

    override val client: HttpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        HttpClient(httpEngine) {
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(HttpTimeout) {
                connectTimeoutMillis = 7000
                requestTimeoutMillis = 7000
            }
        }
    }
}