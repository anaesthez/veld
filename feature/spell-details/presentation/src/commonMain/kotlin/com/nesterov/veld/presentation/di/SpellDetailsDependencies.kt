package com.nesterov.veld.presentation.di

import com.nesterov.veld.common.AppDispatcher
import com.nesterov.veld.domain.SpellDetailsRepository
import com.nesterov.veld.domain.usecases.FetchSpellDetailsUseCase

interface SpellDetailsDependencies {
    val fetchSpellDetailsUseCase: FetchSpellDetailsUseCase
    val dispatcher: AppDispatcher

    class Default(
        repository: SpellDetailsRepository,
        dispatcherProvider: AppDispatcher,
    ): SpellDetailsDependencies {
        override val fetchSpellDetailsUseCase: FetchSpellDetailsUseCase = FetchSpellDetailsUseCase(repository)
        override val dispatcher: AppDispatcher = dispatcherProvider
    }
}