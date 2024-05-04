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

    sealed interface Event {
        data object OnRetryClick : Event
        data object OnBackClick : Event
    }
}

class BestiaryComponentImpl(
    storeFactory: StoreFactory,
    dependencies: BestiaryDependencies,
    componentContext: ComponentContext,
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
            BestiaryComponent.Event.OnBackClick -> {

            }

            BestiaryComponent.Event.OnRetryClick -> {

            }
        }
}
