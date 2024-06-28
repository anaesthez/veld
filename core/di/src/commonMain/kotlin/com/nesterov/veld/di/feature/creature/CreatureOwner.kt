package com.nesterov.veld.di.feature.creature

import com.nesterov.veld.data.CreatureRepositoryImpl
import com.nesterov.veld.di.sources.AppSources
import com.nesterov.veld.domain.CreatureRepository

interface CreatureOwner {
    val repository: CreatureRepository
}

fun <T> AppSources.provideCreature(block: CreatureOwner.() -> T): T {
    return object : CreatureOwner {
        override val repository: CreatureRepository = CreatureRepositoryImpl(
            dndSource = remoteSources.dndSource,
        )
    }.block()
}