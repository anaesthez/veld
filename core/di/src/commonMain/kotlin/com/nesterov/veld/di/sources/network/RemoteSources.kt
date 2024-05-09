package com.nesterov.veld.di.sources.network

import com.nesterov.veld.di.configuration.client.HttpClientConfigImpl
import com.nesterov.veld.network.dnd.DND5eRemoteSource
import com.nesterov.veld.network.dnd.config.HttpClientConfig

interface RemoteSources {
    val dndSource: DND5eRemoteSource
}

object RemoteSourcesImpl : RemoteSources {
    override val dndSource by lazy {
        RemoteSourceDnDModule(
            object : RemoteSourceDnDModule.Dependencies {
                override val config: HttpClientConfig = HttpClientConfigImpl
            }
        ).createDnDSource()
    }
}