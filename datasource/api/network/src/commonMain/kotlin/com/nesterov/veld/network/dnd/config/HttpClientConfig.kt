package com.nesterov.veld.network.dnd.config

import io.ktor.client.HttpClient

interface HttpClientConfig {
    val client: HttpClient
}