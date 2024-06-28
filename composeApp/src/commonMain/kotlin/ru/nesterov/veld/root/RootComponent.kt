package ru.nesterov.veld.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.nesterov.veld.di.graph.provideDependencies
import com.nesterov.veld.presentation.ClassDetailsComponent
import com.nesterov.veld.presentation.ClassDetailsComponentImpl
import com.nesterov.veld.presentation.SpellDetailsComponent
import com.nesterov.veld.presentation.SpellDetailsComponentImpl
import com.nesterov.veld.presentation.creature.CreatureComponent
import com.nesterov.veld.presentation.creature.CreatureComponentImpl
import kotlinx.serialization.Serializable
import ru.nesterov.veld.hub.HubRootComponent
import ru.nesterov.veld.hub.HubRootComponentImpl

interface RootComponent{
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Hub(val component: HubRootComponent): Child
        data class SpellDetails(val component: SpellDetailsComponent): Child
        data class ClassDetails(val component: ClassDetailsComponent) : Child
        data class Creature(val component: CreatureComponent) : Child
    }
}

class RootComponentImpl(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()
    private val storeFactory: DefaultStoreFactory = DefaultStoreFactory()
    private val stack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.Hub,
        handleBackButton = true,
        childFactory = ::createChild
    )
    override val childStack: Value<ChildStack<*, RootComponent.Child>>
        get() = stack

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ): RootComponent.Child =
        when (configuration) {
            is Configuration.Hub -> provideDependencies {
                RootComponent.Child.Hub(
                    HubRootComponentImpl(
                        componentContext = componentContext,
                        storeFactory = storeFactory,
                        bestiaryDependencies = bestiaryDependencies,
                        dependencies = spellDependencies,
                        onAction = ::onHubAction,
                    )
                )
            }

            is Configuration.SpellDetails -> provideDependencies {
                RootComponent.Child.SpellDetails(
                    SpellDetailsComponentImpl(
                        componentContext = componentContext,
                        storeFactory = storeFactory,
                        dependencies = spellDetailsDependencies,
                        spellIndex = configuration.spellIndex,
                        action = ::onSpellDetailsAction,
                    )
                )
            }

            is Configuration.ClassDetails -> provideDependencies {
                RootComponent.Child.ClassDetails(
                    ClassDetailsComponentImpl(
                        componentContext = componentContext,
                        storeFactory = storeFactory,
                        dependencies = classDetailsDependencies,
                        index = configuration.classIndex,
                        action = ::onClassDetailsAction,
                    )
                )
            }

            is Configuration.Creature -> provideDependencies {
                RootComponent.Child.Creature(
                    CreatureComponentImpl(
                        componentContext = componentContext,
                        storeFactory = storeFactory,
                        index = configuration.creatureIndex,
                        dependencies = creatureDependencies,
                        action = ::onCreatureAction,
                    )
                )
            }
        }

    @OptIn(ExperimentalDecomposeApi::class)
    private fun onHubAction(action: HubRootComponent.Action) =
        when(action) {
            is HubRootComponent.Action.NavigateSpellDetails -> {
                navigation.pushNew(Configuration.SpellDetails(spellIndex = action.spellIndex))
            }

            is HubRootComponent.Action.NavigateClassDetails -> {
                navigation.pushNew(Configuration.ClassDetails(classIndex = action.classIndex))
            }

            is HubRootComponent.Action.NavigateCreatureDetails -> {
                navigation.pushNew(Configuration.Creature(creatureIndex = action.creatureIndex))
            }
        }

    @OptIn(ExperimentalDecomposeApi::class)
    private fun onSpellDetailsAction(action: SpellDetailsComponent.Action) =
        when(action) {
            is SpellDetailsComponent.Action.NavigateBack -> {
                navigation.pop()
            }

            is SpellDetailsComponent.Action.NavigateClassDetails -> {
                navigation.pushNew(Configuration.ClassDetails(classIndex = action.classIndex))
            }
        }

    private fun onClassDetailsAction(action: ClassDetailsComponent.Action) =
        when (action) {
            ClassDetailsComponent.Action.NavigateBack -> {
                navigation.pop()
            }
        }

    private fun onCreatureAction(action: CreatureComponent.Action) =
        when (action) {
            CreatureComponent.Action.OnBackClick -> navigation.pop()
        }

    @Serializable
    private sealed interface Configuration {
        @Serializable
        data object Hub : Configuration
        @Serializable
        data class SpellDetails(val spellIndex: String) : Configuration
        @Serializable
        data class ClassDetails(val classIndex: String) : Configuration
        @Serializable
        data class Creature(val creatureIndex: String) : Configuration
    }
}