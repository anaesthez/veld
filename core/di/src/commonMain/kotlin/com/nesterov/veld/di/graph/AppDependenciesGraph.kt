package com.nesterov.veld.di.graph

import com.nesterov.veld.common.AppCoroutineDispatcherProvider
import com.nesterov.veld.di.dispatcher.AppCoroutineDispatcherProviderImpl.Companion.createDefault
import com.nesterov.veld.di.feature.bestiary.BestiaryDataModule
import com.nesterov.veld.di.feature.details.ClassDetailsDataModule
import com.nesterov.veld.di.feature.details.SpellDetailsDataModule
import com.nesterov.veld.di.feature.spell.SpellDataModule
import com.nesterov.veld.di.sources.network.RemoteSourcesHolder
import com.nesterov.veld.presentation.di.BestiaryDependencies
import com.nesterov.veld.presentation.di.ClassDetailsDependencies
import com.nesterov.veld.presentation.di.SpellDependencies
import com.nesterov.veld.presentation.di.SpellDetailsDependencies

class AppDependenciesGraph {
    private val dispatcherProvider: AppCoroutineDispatcherProvider by lazy { createDefault() }

    private val spellDataModule: SpellDataModule by lazy {
        SpellDataModule.Default(
            spellSource = RemoteSourcesHolder.dnD5eRemoteSource,
        )
    }

    private val spellDetailsModule: SpellDetailsDataModule by lazy {
        SpellDetailsDataModule.Default(
            spellSource = RemoteSourcesHolder.dnD5eRemoteSource,
        )
    }

    private val classDetailsModule: ClassDetailsDataModule by lazy {
        ClassDetailsDataModule.Default(
            dnd5eRemoteSource = RemoteSourcesHolder.dnD5eRemoteSource,
        )
    }

    private val bestiaryModule: BestiaryDataModule by lazy {
        BestiaryDataModule.Default(
            dndSource = RemoteSourcesHolder.dnD5eRemoteSource,
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

    val classDetailsDependencies: ClassDetailsDependencies by lazy {
        ClassDetailsDependencies.Default(
            dispatcherProvider = dispatcherProvider,
            repository = classDetailsModule.repository,
        )
    }

    val bestiaryDependencies: BestiaryDependencies by lazy {
        BestiaryDependencies.Default(
            dispatcherProvider = dispatcherProvider,
            repository = bestiaryModule.repository,
        )
    }
}