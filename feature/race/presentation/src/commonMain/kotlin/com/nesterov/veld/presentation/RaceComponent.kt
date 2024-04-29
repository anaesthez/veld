package com.nesterov.veld.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.common.base.BaseComponent

sealed interface RaceComponent {
    val state: Value<RaceStore.State>
}

class RaceComponentImpl(
    storeFactory: StoreFactory,
    componentContext: ComponentContext
): BaseComponent(componentContext), RaceComponent {
    private val raceStore = instanceKeeper.getStore {
        RaceStoreFactory(
            storeFactory = storeFactory,
        ).create()
    }
    override val state: Value<RaceStore.State> = raceStore.asValue()
}
