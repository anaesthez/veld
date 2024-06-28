package com.nesterov.veld.di.sources.local

import com.nesterov.veld.creature.BestiaryLocalSource
import com.nesterov.veld.db.AppDatabase
import com.nesterov.veld.di.dispatcher.provideDispatcher

interface LocalSources {
    val bestiaryLocalSource: BestiaryLocalSource
}

object LocalSourcesImpl : LocalSources {
    override val bestiaryLocalSource: BestiaryLocalSource by lazy {
        provideDispatcher {
            LocalCreatureModule(
                dependencies = object : LocalCreatureModule.Dependencies {
                    override val appDatabase: AppDatabase = AppDatabase(
                        appDispatcher = appDispatcher,
                    )
                }
            ).createLocalCreatureSource()
        }
    }
}