package com.nesterov.veld.di.feature.details

import com.nesterov.veld.data.ClassDetailsRepositoryImpl
import com.nesterov.veld.domain.ClassDetailsRepository
import com.nesterov.veld.network.dnd.DNDSpellSource

interface ClassDetailsDataModule {
    val repository: ClassDetailsRepository

    class Default(
        dndSpellSource: DNDSpellSource,
    ) : ClassDetailsDataModule {
        override val repository: ClassDetailsRepository by lazy {
            ClassDetailsRepositoryImpl(
                spellSource = dndSpellSource,
            )
        }
    }
}