package com.nesterov.veld.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.common.base.BaseComponent

sealed interface BackstoryComponent {
    val state: Value<BackstoryStore.State>
}

class BackstoryComponentImpl(
    storeFactory: StoreFactory,
    componentContext: ComponentContext
): BaseComponent(componentContext), BackstoryComponent {
    private val backstoryStore = instanceKeeper.getStore {
        BackstoryStoreFactory(
            storeFactory = storeFactory,
        ).create()
    }
    override val state: Value<BackstoryStore.State> = backstoryStore.asValue()
}
