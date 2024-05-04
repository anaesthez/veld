package com.nesterov.veld.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.common.base.BaseComponent

sealed interface BestiaryComponent {
    val state: Value<BestiaryStore.State>
}

class BestiaryComponentImpl(
    storeFactory: StoreFactory,
    componentContext: ComponentContext
) : BaseComponent(componentContext), BestiaryComponent {
    private val bestiaryStore = instanceKeeper.getStore {
        BestiaryStoreFactory(
            storeFactory = storeFactory,
        ).create()
    }
    override val state: Value<BestiaryStore.State> = bestiaryStore.asValue()
}
