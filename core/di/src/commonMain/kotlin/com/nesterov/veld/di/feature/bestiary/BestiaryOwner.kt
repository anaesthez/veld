package com.nesterov.veld.di.feature.bestiary

import com.nesterov.veld.data.BestiaryRepositoryImpl
import com.nesterov.veld.di.sources.network.RemoteSources
import com.nesterov.veld.domain.BestiaryRepository

interface BestiaryOwner {
    val repository: BestiaryRepository
}

fun <T> RemoteSources.provideBestiary(block: BestiaryOwner.() -> T): T {
    val bestiarySource = this.dndSource
    return object : BestiaryOwner {
        override val repository: BestiaryRepository = BestiaryRepositoryImpl(
            bestiarySource = bestiarySource,
        )
    }.block()
}