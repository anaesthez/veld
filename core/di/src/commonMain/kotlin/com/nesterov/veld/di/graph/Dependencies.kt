package com.nesterov.veld.di.graph

import com.nesterov.veld.di.dispatcher.provideDispatcher
import com.nesterov.veld.di.feature.bestiary.provideBestiary
import com.nesterov.veld.di.feature.creature.provideCreature
import com.nesterov.veld.di.feature.details.provideClassDetails
import com.nesterov.veld.di.feature.details.provideSpellDetails
import com.nesterov.veld.di.feature.spell.provideSpells
import com.nesterov.veld.di.sources.AppSources
import com.nesterov.veld.presentation.creature.di.CreatureDependencies
import com.nesterov.veld.presentation.di.BestiaryDependencies
import com.nesterov.veld.presentation.di.ClassDetailsDependencies
import com.nesterov.veld.presentation.di.SpellDependencies
import com.nesterov.veld.presentation.di.SpellDetailsDependencies

object Dependencies : DependenciesScope {
    private val context = object : AppSources {}

    override val spellDependencies: SpellDependencies by lazy {
        with(context) {
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
        with(context) {
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
        with(context) {
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
        with(context) {
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
        with(context) {
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