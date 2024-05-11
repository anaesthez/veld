package com.nesterov.veld.di.feature.details

import com.nesterov.veld.data.SpellDetailsRepositoryImpl
import com.nesterov.veld.di.sources.network.RemoteSources
import com.nesterov.veld.domain.SpellDetailsRepository

interface SpellDetailsOwner {
    val repository: SpellDetailsRepository
}

fun <T> RemoteSources.provideSpellDetails(block: SpellDetailsOwner.() -> T): T {
    val detailsSource = this.dndSource
    return object : SpellDetailsOwner {
        override val repository: SpellDetailsRepository by lazy {
            SpellDetailsRepositoryImpl(
                detailsSource = detailsSource,
            )
        }
    }.block()
}