package com.nesterov.veld.presentation.di

import com.nesterov.veld.common.AppCoroutineDispatcherProvider
import com.nesterov.veld.domain.SpellDetailsRepository
import com.nesterov.veld.domain.usecases.FetchSpellDetailsUseCase

interface SpellDetailsDependencies {
    val fetchSpellDetailsUseCase: FetchSpellDetailsUseCase
    val dispatcher: AppCoroutineDispatcherProvider

    class Default(
        repository: SpellDetailsRepository,
        dispatcherProvider: AppCoroutineDispatcherProvider,
    ): SpellDetailsDependencies {
        override val fetchSpellDetailsUseCase: FetchSpellDetailsUseCase = FetchSpellDetailsUseCase(repository)
        override val dispatcher: AppCoroutineDispatcherProvider = dispatcherProvider
    }
}