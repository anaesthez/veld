package com.nesterov.veld.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.common.base.BaseComponent
import com.nesterov.veld.presentation.di.CreatureDependencies

interface CreatureComponent {
    val state: Value<CreatureStore.State>

    fun onObtainEvent(event: Event)

    sealed interface Event {
        data object OnRetryClick : Event
        data object OnBackClick : Event
        data class OnSearchCreature(val query: String) : Event
    }
}

class CreatureComponentImpl(
    storeFactory: StoreFactory,
    index: String,
    dependencies: CreatureDependencies,
    componentContext: ComponentContext,
) : BaseComponent(componentContext), CreatureComponent {
    private val bestiaryStore = instanceKeeper.getStore {
        CreatureStoreFactory(
            storeFactory = storeFactory,
            dependencies = dependencies,
            index = index,
        ).create()
    }
    override val state: Value<CreatureStore.State> = bestiaryStore.asValue()

    override fun onObtainEvent(event: CreatureComponent.Event) =
        when (event) {
            CreatureComponent.Event.OnBackClick -> {

            }

            CreatureComponent.Event.OnRetryClick -> {

            }

            is CreatureComponent.Event.OnSearchCreature -> {
                bestiaryStore.accept(CreatureStore.Intent.OnSearchCreature(event.query))
            }
        }
}
