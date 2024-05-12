package com.nesterov.veld.presentation.di

import com.nesterov.veld.common.AppDispatcher
import com.nesterov.veld.domain.CreatureRepository
import com.nesterov.veld.domain.FetchCreatureListUseCase

interface BestiaryDependencies {
    val fetchCreatureListUseCase: FetchCreatureListUseCase
    val dispatcher: AppDispatcher

    class Default(
        repository: CreatureRepository,
        dispatcherProvider: AppDispatcher,
    ) : BestiaryDependencies {
        override val fetchCreatureListUseCase: FetchCreatureListUseCase =
            FetchCreatureListUseCase(repository)
        override val dispatcher: AppDispatcher = dispatcherProvider
    }
}