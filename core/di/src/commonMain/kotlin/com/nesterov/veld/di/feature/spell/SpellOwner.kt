package com.nesterov.veld.di.feature.spell

import com.nesterov.veld.data.SpellRepositoryImpl
import com.nesterov.veld.di.sources.network.RemoteSources
import com.nesterov.veld.domain.SpellRepository

interface SpellOwner {
    val repository: SpellRepository
}

fun <T> RemoteSources.provideSpells(block: SpellOwner.() -> T): T {
    val spellSource = this.dndSource
    return object : SpellOwner {
        override val repository: SpellRepository = SpellRepositoryImpl(
            spellSource = spellSource,
        )
    }.block()
}