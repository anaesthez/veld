package com.nesterov.veld.presentation.di

import com.nesterov.veld.common.AppDispatcher
import com.nesterov.veld.domain.BestiaryRepository
import com.nesterov.veld.domain.FetchCreatureLocalListUseCase
import com.nesterov.veld.domain.FetchCreatureRemoteListUseCase

interface BestiaryDependencies {
    val fetchCreatureRemoteListUseCase: FetchCreatureRemoteListUseCase
    val fetchCreatureLocalListUseCase: FetchCreatureLocalListUseCase
    val dispatcher: AppDispatcher

    class Default(
        repository: BestiaryRepository,
        dispatcherProvider: AppDispatcher,
    ) : BestiaryDependencies {
        override val fetchCreatureRemoteListUseCase: FetchCreatureRemoteListUseCase =
            FetchCreatureRemoteListUseCase(repository)
        override val fetchCreatureLocalListUseCase: FetchCreatureLocalListUseCase =
            FetchCreatureLocalListUseCase(repository)
        override val dispatcher: AppDispatcher = dispatcherProvider
    }
}