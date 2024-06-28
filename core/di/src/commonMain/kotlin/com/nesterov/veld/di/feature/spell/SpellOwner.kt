package com.nesterov.veld.di.feature.spell

import com.nesterov.veld.data.SpellRepositoryImpl
import com.nesterov.veld.di.sources.AppSources
import com.nesterov.veld.domain.SpellRepository

interface SpellOwner {
    val repository: SpellRepository
}

fun <T> AppSources.provideSpells(block: SpellOwner.() -> T): T {
    return object : SpellOwner {
        override val repository: SpellRepository = SpellRepositoryImpl(
            spellSource = remoteSources.dndSource,
        )
    }.block()
}