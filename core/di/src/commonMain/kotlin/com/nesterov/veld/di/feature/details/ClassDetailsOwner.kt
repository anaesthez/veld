package com.nesterov.veld.di.feature.details

import com.nesterov.veld.data.ClassDetailsRepositoryImpl
import com.nesterov.veld.di.sources.AppSources
import com.nesterov.veld.domain.ClassDetailsRepository

interface ClassDetailsOwner {
    val repository: ClassDetailsRepository
}

fun <T> AppSources.provideClassDetails(block: ClassDetailsOwner.() -> T): T {
    return object : ClassDetailsOwner {
        override val repository: ClassDetailsRepository by lazy {
            ClassDetailsRepositoryImpl(
                classDetailsSource = remoteSources.dndSource,
            )
        }
    }.block()
}
