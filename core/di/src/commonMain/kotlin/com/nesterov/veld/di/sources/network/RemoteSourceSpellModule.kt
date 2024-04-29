package com.nesterov.veld.di.sources.network

import com.nesterov.veld.network.dnd.DNDSpellSource
import com.nesterov.veld.network.dnd.DNDSpellSourceImpl
import com.nesterov.veld.network.dnd.config.HttpClientConfig
import com.nesterov.veld.network.dnd.config.HttpRequestWrapper

class RemoteSourceSpellModule(
    private val dependencies: Dependencies,
) {
    fun createSpellSource(): DNDSpellSource =
        DNDSpellSourceImpl(
            dependencies = object : DNDSpellSource.Dependencies, Dependencies by dependencies { }
        )

    interface Dependencies {
        val config: HttpClientConfig
        val wrapper: HttpRequestWrapper
    }
}