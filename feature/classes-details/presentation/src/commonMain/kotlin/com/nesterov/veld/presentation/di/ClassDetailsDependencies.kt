package com.nesterov.veld.presentation.di

import com.nesterov.veld.common.AppCoroutineDispatcherProvider
import com.nesterov.veld.domain.ClassDetailsRepository
import com.nesterov.veld.domain.usecase.FetchClassDetailsUseCase

interface ClassDetailsDependencies {
    val fetchClassDetails: FetchClassDetailsUseCase
    val dispatcher: AppCoroutineDispatcherProvider

    class Default(
        repository: ClassDetailsRepository,
        dispatcherProvider: AppCoroutineDispatcherProvider,
    ) : ClassDetailsDependencies {
        override val fetchClassDetails: FetchClassDetailsUseCase =
            FetchClassDetailsUseCase(repository)
        override val dispatcher: AppCoroutineDispatcherProvider = dispatcherProvider
    }
}