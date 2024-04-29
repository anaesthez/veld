package com.nesterov.veld.di.graph

import com.nesterov.veld.common.AppCoroutineDispatcherProvider
import com.nesterov.veld.di.dispatcher.AppCoroutineDispatcherProviderImpl.Companion.createDefault
import com.nesterov.veld.di.feature.details.SpellDetailsDataModule
import com.nesterov.veld.di.feature.spell.SpellDataModule
import com.nesterov.veld.di.sources.network.RemoteSourcesHolder
import com.nesterov.veld.presentation.di.SpellDependencies
import com.nesterov.veld.presentation.di.SpellDetailsDependencies

class AppDependenciesGraph {
    private val dispatcherProvider: AppCoroutineDispatcherProvider by lazy { createDefault() }

    private val spellDataModule: SpellDataModule by lazy {
        SpellDataModule.Default(
            spellSource = RemoteSourcesHolder.dndSpellSource,
        )
    }

    private val spellDetailsModule: SpellDetailsDataModule by lazy {
        SpellDetailsDataModule.Default(
            spellSource = RemoteSourcesHolder.dndSpellSource,
        )
    }

    val spellDependencies: SpellDependencies by lazy {
        SpellDependencies.Default(
            dispatcherProvider = dispatcherProvider,
            repository = spellDataModule.repository,
        )
    }

    val spellDetailsDependencies: SpellDetailsDependencies by lazy {
        SpellDetailsDependencies.Default(
            dispatcherProvider = dispatcherProvider,
            repository = spellDetailsModule.repository,
        )
    }
}