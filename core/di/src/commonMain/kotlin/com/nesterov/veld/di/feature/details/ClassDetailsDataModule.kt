package com.nesterov.veld.di.feature.details

import com.nesterov.veld.data.ClassDetailsRepositoryImpl
import com.nesterov.veld.domain.ClassDetailsRepository
import com.nesterov.veld.network.dnd.DND5eRemoteSource

interface ClassDetailsDataModule {
    val repository: ClassDetailsRepository

    class Default(
        dnd5eRemoteSource: DND5eRemoteSource,
    ) : ClassDetailsDataModule {
        override val repository: ClassDetailsRepository by lazy {
            ClassDetailsRepositoryImpl(
                spellSource = dnd5eRemoteSource,
            )
        }
    }
}