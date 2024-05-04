package com.nesterov.veld.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.common.base.BaseComponent
import com.nesterov.veld.presentation.di.SpellDependencies
import com.nesterov.veld.presentation.model.SpellPresentationModel

sealed interface SpellComponent {
    val state: Value<SpellStore.State>

    fun onObtainEvent(event: Event)

    sealed interface Event {
        data object OnRetryClick : Event
        data class OnSpellClick(val spell: SpellPresentationModel): Event
        data class SearchSpell(val input: String): Event
    }

    sealed interface Action {
        data class NavigateSpellDetails(val index: String): Action
    }
}

class SpellComponentImpl(
    storeFactory: StoreFactory,
    dependencies: SpellDependencies,
    componentContext: ComponentContext,
    private val action: (SpellComponent.Action) -> Unit,
): BaseComponent(componentContext), SpellComponent {
    private val spellsStore = instanceKeeper.getStore {
        SpellStoreFactory(
            storeFactory = storeFactory,
            dependencies = dependencies,
        ).create()
    }
    override val state: Value<SpellStore.State> = spellsStore.asValue()

    override fun onObtainEvent(event: SpellComponent.Event) =
        when (event) {
            is SpellComponent.Event.OnSpellClick -> {
                action(SpellComponent.Action.NavigateSpellDetails(event.spell.index))
            }

            is SpellComponent.Event.SearchSpell -> {
                spellsStore.accept(SpellStore.Intent.SearchSpell(event.input))
            }

            SpellComponent.Event.OnRetryClick -> {
                TODO()
            }
        }
}
