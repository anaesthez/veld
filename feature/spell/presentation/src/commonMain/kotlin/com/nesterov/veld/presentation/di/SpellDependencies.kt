package com.nesterov.veld.presentation.di

import com.nesterov.veld.common.AppDispatcher
import com.nesterov.veld.domain.SpellRepository
import com.nesterov.veld.domain.usecases.FetchSpellListUseCase

interface SpellDependencies {
    val fetchSpellListUseCase: FetchSpellListUseCase
    val dispatcher: AppDispatcher

    class Default(
        repository: SpellRepository,
        dispatcherProvider: AppDispatcher,
    ): SpellDependencies {
        override val fetchSpellListUseCase: FetchSpellListUseCase = FetchSpellListUseCase(repository)
        override val dispatcher: AppDispatcher = dispatcherProvider
    }
}