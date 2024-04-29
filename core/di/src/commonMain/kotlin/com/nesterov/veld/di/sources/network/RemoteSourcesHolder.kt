package com.nesterov.veld.di.sources.network

import com.nesterov.veld.di.configuration.client.HttpClientConfigImpl
import com.nesterov.veld.di.configuration.wrapper.HttpRequestWrapperImpl
import com.nesterov.veld.network.dnd.config.HttpClientConfig
import com.nesterov.veld.network.dnd.config.HttpRequestWrapper

object RemoteSourcesHolder {
    private val config: HttpClientConfig by lazy {
        HttpClientConfigImpl()
    }
    private val wrapper: HttpRequestWrapper by lazy {
        HttpRequestWrapperImpl()
    }

    val dndSpellSource by lazy {
        RemoteSourceSpellModule(
            object : RemoteSourceSpellModule.Dependencies {
                override val config: HttpClientConfig = RemoteSourcesHolder.config
                override val wrapper: HttpRequestWrapper = RemoteSourcesHolder.wrapper
            }
        ).createSpellSource()
    }
}