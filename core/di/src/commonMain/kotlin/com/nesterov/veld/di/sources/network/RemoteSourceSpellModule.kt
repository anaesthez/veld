package com.nesterov.veld.di.sources.network

import com.nesterov.veld.network.dnd.DND5eRemoteSource
import com.nesterov.veld.network.dnd.DND5eRemoteSourceImpl
import com.nesterov.veld.network.dnd.config.HttpClientConfig
import com.nesterov.veld.network.dnd.config.HttpRequestWrapper

class RemoteSourceSpellModule(
    private val dependencies: Dependencies,
) {
    fun createSpellSource(): DND5eRemoteSource =
        DND5eRemoteSourceImpl(
            dependencies = object : DND5eRemoteSource.Dependencies, Dependencies by dependencies {}
        )

    interface Dependencies {
        val config: HttpClientConfig
        val wrapper: HttpRequestWrapper
    }
}