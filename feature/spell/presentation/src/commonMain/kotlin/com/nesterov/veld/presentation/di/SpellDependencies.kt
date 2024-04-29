package com.nesterov.veld.presentation.di

import com.nesterov.veld.common.AppCoroutineDispatcherProvider
import com.nesterov.veld.domain.SpellRepository
import com.nesterov.veld.domain.usecases.FetchSpellListUseCase

interface SpellDependencies {
    val fetchSpellListUseCase: FetchSpellListUseCase
    val dispatcher: AppCoroutineDispatcherProvider

    class Default(
        repository: SpellRepository,
        dispatcherProvider: AppCoroutineDispatcherProvider,
    ): SpellDependencies {
        override val fetchSpellListUseCase: FetchSpellListUseCase = FetchSpellListUseCase(repository)
        override val dispatcher: AppCoroutineDispatcherProvider = dispatcherProvider
    }
}