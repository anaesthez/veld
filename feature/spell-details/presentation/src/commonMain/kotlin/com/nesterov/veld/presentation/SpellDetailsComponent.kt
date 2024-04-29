package com.nesterov.veld.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.common.base.BaseComponent
import com.nesterov.veld.presentation.di.SpellDetailsDependencies

sealed interface SpellDetailsComponent {
    val state: Value<SpellDetailsStore.State>

    fun onObtainEvent(event: Event)

    sealed interface Event {
        data object OnClassClick: Event
        data object OnSubclassClick: Event
        data object OnBackClick: Event
    }

    sealed interface Action {
        data object NavigateBack: Action
    }
}

class SpellDetailsComponentImpl(
    storeFactory: StoreFactory,
    dependencies: SpellDetailsDependencies,
    spellIndex: String,
    componentContext: ComponentContext,
    private val action: (SpellDetailsComponent.Action) -> Unit,
): BaseComponent(componentContext), SpellDetailsComponent {
    private val spellsStore = instanceKeeper.getStore {
        SpellDetailsDetailsStoreFactory(
            spellIndex = spellIndex,
            storeFactory = storeFactory,
            dependencies = dependencies,
        ).create()
    }
    override val state: Value<SpellDetailsStore.State> = spellsStore.asValue()

    override fun onObtainEvent(event: SpellDetailsComponent.Event) =
        when(event) {
            SpellDetailsComponent.Event.OnBackClick -> {
                action(SpellDetailsComponent.Action.NavigateBack)
            }

            SpellDetailsComponent.Event.OnClassClick -> {

            }

            SpellDetailsComponent.Event.OnSubclassClick -> {
                
            }
        }
}
