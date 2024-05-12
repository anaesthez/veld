package com.nesterov.veld.di.feature.creature

import com.nesterov.veld.data.CreatureRepositoryImpl
import com.nesterov.veld.di.sources.network.RemoteSources
import com.nesterov.veld.domain.CreatureRepository

interface CreatureOwner {
    val repository: CreatureRepository
}

fun <T> RemoteSources.provideCreature(block: CreatureOwner.() -> T): T {
    val creatureSource = this.dndSource
    return object : CreatureOwner {
        override val repository: CreatureRepository = CreatureRepositoryImpl(
            dndSource = creatureSource,
        )
    }.block()
}