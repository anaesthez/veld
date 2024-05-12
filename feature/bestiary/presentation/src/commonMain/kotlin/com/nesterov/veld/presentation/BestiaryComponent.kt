package com.nesterov.veld.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.common.base.BaseComponent
import com.nesterov.veld.presentation.di.BestiaryDependencies

interface BestiaryComponent {
    val state: Value<BestiaryStore.State>

    fun onObtainEvent(event: Event)

    sealed interface Action {
        data class OnCreatureClick(val index: String) : Action
    }

    sealed interface Event {
        data object OnRetryClick : Event
        data class OnCreatureClick(val index: String) : Event
        data class OnSearchCreature(val query: String) : Event
    }
}

class BestiaryComponentImpl(
    storeFactory: StoreFactory,
    dependencies: BestiaryDependencies,
    componentContext: ComponentContext,
    private val action: (BestiaryComponent.Action) -> Unit,
) : BaseComponent(componentContext), BestiaryComponent {
    private val bestiaryStore = instanceKeeper.getStore {
        BestiaryStoreFactory(
            storeFactory = storeFactory,
            dependencies = dependencies,
        ).create()
    }
    override val state: Value<BestiaryStore.State> = bestiaryStore.asValue()

    override fun onObtainEvent(event: BestiaryComponent.Event) =
        when (event) {
            BestiaryComponent.Event.OnRetryClick -> {

            }

            is BestiaryComponent.Event.OnCreatureClick -> {
                action(BestiaryComponent.Action.OnCreatureClick(event.index))
            }

            is BestiaryComponent.Event.OnSearchCreature -> {
                bestiaryStore.accept(BestiaryStore.Intent.OnSearchCreature(event.query))
            }
        }
}
