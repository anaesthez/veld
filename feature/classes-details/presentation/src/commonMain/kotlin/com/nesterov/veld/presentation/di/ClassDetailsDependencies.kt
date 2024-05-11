package com.nesterov.veld.presentation.di

import com.nesterov.veld.common.AppDispatcher
import com.nesterov.veld.domain.ClassDetailsRepository
import com.nesterov.veld.domain.usecase.FetchClassDetailsUseCase

interface ClassDetailsDependencies {
    val fetchClassDetails: FetchClassDetailsUseCase
    val dispatcher: AppDispatcher

    class Default(
        repository: ClassDetailsRepository,
        dispatcherProvider: AppDispatcher,
    ) : ClassDetailsDependencies {
        override val fetchClassDetails: FetchClassDetailsUseCase =
            FetchClassDetailsUseCase(repository)
        override val dispatcher: AppDispatcher = dispatcherProvider
    }
}