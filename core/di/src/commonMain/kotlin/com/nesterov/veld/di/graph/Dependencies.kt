package com.nesterov.veld.di.graph

import com.nesterov.veld.common.AppDispatcher
import com.nesterov.veld.di.dispatcher.AppDispatcherImpl
import com.nesterov.veld.di.dispatcher.provideDispatcher
import com.nesterov.veld.di.feature.bestiary.provideBestiary
import com.nesterov.veld.di.feature.creature.provideCreature
import com.nesterov.veld.di.feature.details.provideClassDetails
import com.nesterov.veld.di.feature.details.provideSpellDetails
import com.nesterov.veld.di.feature.spell.provideSpells
import com.nesterov.veld.di.sources.network.RemoteSources
import com.nesterov.veld.di.sources.network.RemoteSourcesImpl
import com.nesterov.veld.presentation.creature.di.CreatureDependencies
import com.nesterov.veld.presentation.di.BestiaryDependencies
import com.nesterov.veld.presentation.di.ClassDetailsDependencies
import com.nesterov.veld.presentation.di.SpellDependencies
import com.nesterov.veld.presentation.di.SpellDetailsDependencies

object Dependencies : DependenciesScope {
    private val contexts = object : AppDispatcher by AppDispatcherImpl,
        RemoteSources by RemoteSourcesImpl {}

    override val spellDependencies: SpellDependencies by lazy {
        with(contexts) {
            provideDispatcher {
                provideSpells {
                    SpellDependencies.Default(
                        dispatcherProvider = appDispatcher,
                        repository = repository,
                    )
                }
            }
        }
    }

    override val spellDetailsDependencies: SpellDetailsDependencies by lazy {
        with(contexts) {
            provideDispatcher {
                provideSpellDetails {
                    SpellDetailsDependencies.Default(
                        dispatcherProvider = appDispatcher,
                        repository = repository,
                    )
                }
            }
        }
    }

    override val classDetailsDependencies: ClassDetailsDependencies by lazy {
        with(contexts) {
            provideDispatcher {
                provideClassDetails {
                    ClassDetailsDependencies.Default(
                        dispatcherProvider = appDispatcher,
                        repository = repository,
                    )
                }
            }
        }
    }

    override val bestiaryDependencies: BestiaryDependencies by lazy {
        with(contexts) {
            provideDispatcher {
                provideBestiary {
                    BestiaryDependencies.Default(
                        dispatcherProvider = appDispatcher,
                        repository = repository,
                    )
                }
            }
        }
    }

    override val creatureDependencies: CreatureDependencies by lazy {
        with(contexts) {
            provideDispatcher {
                provideCreature {
                    CreatureDependencies.Default(
                        dispatcherProvider = appDispatcher,
                        repository = repository,
                    )
                }
            }
        }
    }
}