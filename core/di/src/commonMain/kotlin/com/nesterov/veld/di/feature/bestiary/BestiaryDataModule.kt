package com.nesterov.veld.di.feature.bestiary

import com.nesterov.veld.data.BestiaryRepositoryImpl
import com.nesterov.veld.domain.BestiaryRepository
import com.nesterov.veld.network.dnd.DND5eRemoteSource

interface BestiaryDataModule {
    val repository: BestiaryRepository

    class Default(
        dndSource: DND5eRemoteSource,
    ) : BestiaryDataModule {
        override val repository: BestiaryRepository by lazy {
            BestiaryRepositoryImpl(
                dnd5eRemoteSource = dndSource,
            )
        }
    }
}