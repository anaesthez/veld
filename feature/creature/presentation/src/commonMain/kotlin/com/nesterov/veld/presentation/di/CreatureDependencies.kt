package com.nesterov.veld.presentation.di

import com.nesterov.veld.common.AppDispatcher
import com.nesterov.veld.domain.CreatureRepository
import com.nesterov.veld.domain.FetchCreatureUseCase

interface CreatureDependencies {
    val fetchCreatureUseCase: FetchCreatureUseCase
    val dispatcher: AppDispatcher

    class Default(
        repository: CreatureRepository,
        dispatcherProvider: AppDispatcher,
    ) : CreatureDependencies {
        override val fetchCreatureUseCase: FetchCreatureUseCase = FetchCreatureUseCase(repository)
        override val dispatcher: AppDispatcher = dispatcherProvider
    }
}