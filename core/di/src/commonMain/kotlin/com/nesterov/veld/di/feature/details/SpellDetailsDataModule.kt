package com.nesterov.veld.di.feature.details

import com.nesterov.veld.data.SpellDetailsRepositoryImpl
import com.nesterov.veld.domain.SpellDetailsRepository
import com.nesterov.veld.network.dnd.DNDSpellSource

interface SpellDetailsDataModule {
    val repository: SpellDetailsRepository

    class Default(
        spellSource: DNDSpellSource,
    ): SpellDetailsDataModule {
        override val repository: SpellDetailsRepository by lazy {
            SpellDetailsRepositoryImpl(
                spellSource,
            )
        }
    }
}