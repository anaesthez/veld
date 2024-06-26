package com.nesterov.veld.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.common.base.BaseComponent

sealed interface ItemComponent {
    val state: Value<SpellsStore.State>
}

class ItemComponentImpl(
    storeFactory: StoreFactory,
    componentContext: ComponentContext
): BaseComponent(componentContext), ItemComponent {
    private val itemStore = instanceKeeper.getStore {
        SpellsStoreFactory(
            storeFactory = storeFactory,
        ).create()
    }
    override val state: Value<SpellsStore.State> = itemStore.asValue()
}
