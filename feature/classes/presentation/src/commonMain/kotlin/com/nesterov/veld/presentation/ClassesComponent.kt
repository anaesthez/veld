package com.nesterov.veld.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.common.base.BaseComponent

sealed interface ClassesComponent {
    val state: Value<ClassesStore.State>
}

class ClassesComponentImpl(
    storeFactory: StoreFactory,
    componentContext: ComponentContext
): BaseComponent(componentContext), ClassesComponent {
    private val classesStore = instanceKeeper.getStore {
        ClassesStoreFactory(
            storeFactory = storeFactory,
        ).create()
    }
    override val state: Value<ClassesStore.State> = classesStore.asValue()
}
