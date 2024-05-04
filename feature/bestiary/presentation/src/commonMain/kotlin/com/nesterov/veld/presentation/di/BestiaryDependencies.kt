package com.nesterov.veld.presentation.di

import com.nesterov.veld.common.AppCoroutineDispatcherProvider
import com.nesterov.veld.domain.BestiaryRepository
import com.nesterov.veld.domain.FetchCreatureListUseCase

interface BestiaryDependencies {
    val fetchCreatureListUseCase: FetchCreatureListUseCase
    val dispatcher: AppCoroutineDispatcherProvider

    class Default(
        repository: BestiaryRepository,
        dispatcherProvider: AppCoroutineDispatcherProvider,
    ) : BestiaryDependencies {
        override val fetchCreatureListUseCase: FetchCreatureListUseCase =
            FetchCreatureListUseCase(repository)
        override val dispatcher: AppCoroutineDispatcherProvider = dispatcherProvider
    }
}