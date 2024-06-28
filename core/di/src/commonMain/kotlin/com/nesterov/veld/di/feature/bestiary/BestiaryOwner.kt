package com.nesterov.veld.di.feature.bestiary

import com.nesterov.veld.data.BestiaryRepositoryImpl
import com.nesterov.veld.di.sources.AppSources
import com.nesterov.veld.domain.BestiaryRepository

interface BestiaryOwner {
    val repository: BestiaryRepository
}

fun <T> AppSources.provideBestiary(block: BestiaryOwner.() -> T): T {
    return object : BestiaryOwner {
        override val repository: BestiaryRepository = BestiaryRepositoryImpl(
            bestiaryRemoteSource = remoteSources.dndSource,
            bestiaryLocalSource = localSources.bestiaryLocalSource,
        )
    }.block()
}