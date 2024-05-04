package com.nesterov.veld.di.feature.spell

import com.nesterov.veld.data.SpellRepositoryImpl
import com.nesterov.veld.domain.SpellRepository
import com.nesterov.veld.network.dnd.DND5eRemoteSource

interface SpellDataModule {
    val repository: SpellRepository

    class Default(
        spellSource: DND5eRemoteSource,
    ): SpellDataModule {
        override val repository: SpellRepository by lazy {
            SpellRepositoryImpl(
                spellSource,
            )
        }
    }
}