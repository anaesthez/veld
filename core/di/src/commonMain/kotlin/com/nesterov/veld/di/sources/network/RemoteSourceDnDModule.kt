package com.nesterov.veld.di.sources.network

import com.nesterov.veld.network.dnd.DND5eRemoteSource
import com.nesterov.veld.network.dnd.DND5eRemoteSourceImpl
import com.nesterov.veld.network.dnd.config.HttpClientConfig

class RemoteSourceDnDModule(
    private val dependencies: Dependencies,
) {
    fun createDnDSource(): DND5eRemoteSource =
        DND5eRemoteSourceImpl(
            dependencies = object : DND5eRemoteSourceImpl.Dependencies,
                Dependencies by dependencies {}
        )

    interface Dependencies {
        val config: HttpClientConfig
    }
}