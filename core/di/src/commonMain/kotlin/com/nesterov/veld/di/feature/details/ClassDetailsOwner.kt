package com.nesterov.veld.di.feature.details

import com.nesterov.veld.data.ClassDetailsRepositoryImpl
import com.nesterov.veld.di.sources.network.RemoteSources
import com.nesterov.veld.domain.ClassDetailsRepository

interface ClassDetailsOwner {
    val repository: ClassDetailsRepository
}

fun <T> RemoteSources.provideClassDetails(block: ClassDetailsOwner.() -> T): T {
    val classDetailsSource = this.dndSource
    return object : ClassDetailsOwner {
        override val repository: ClassDetailsRepository by lazy {
            ClassDetailsRepositoryImpl(
                classDetailsSource = classDetailsSource,
            )
        }
    }.block()
}
