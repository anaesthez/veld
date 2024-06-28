package com.nesterov.veld.di.sources.local

import com.nesterov.veld.creature.BestiaryLocalSource
import com.nesterov.veld.creature.BestiaryLocalSourceImpl
import com.nesterov.veld.db.AppDatabase

class LocalCreatureModule(private val dependencies: Dependencies) {
    fun createLocalCreatureSource(): BestiaryLocalSource =
        BestiaryLocalSourceImpl(
            dependencies = object : BestiaryLocalSourceImpl.Dependencies,
                Dependencies by dependencies {
                override val database: AppDatabase
                    get() = dependencies.appDatabase
            }
        )

    interface Dependencies {
        val appDatabase: AppDatabase
    }
}