package com.nesterov.veld.di.feature.details

import com.nesterov.veld.data.SpellDetailsRepositoryImpl
import com.nesterov.veld.di.sources.AppSources
import com.nesterov.veld.domain.SpellDetailsRepository

interface SpellDetailsOwner {
    val repository: SpellDetailsRepository
}

fun <T> AppSources.provideSpellDetails(block: SpellDetailsOwner.() -> T): T {
    return object : SpellDetailsOwner {
        override val repository: SpellDetailsRepository by lazy {
            SpellDetailsRepositoryImpl(
                detailsSource = remoteSources.dndSource,
            )
        }
    }.block()
}