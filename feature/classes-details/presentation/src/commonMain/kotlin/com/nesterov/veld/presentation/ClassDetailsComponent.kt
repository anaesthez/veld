package com.nesterov.veld.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.common.base.BaseComponent
import com.nesterov.veld.presentation.di.ClassDetailsDependencies

sealed interface ClassDetailsComponent {
    val state: Value<ClassDetailsStore.State>
}

class ClassDetailsComponentImpl(
    storeFactory: StoreFactory,
    index: String,
    classDetailsDependencies: ClassDetailsDependencies,
    componentContext: ComponentContext,
) : BaseComponent(componentContext), ClassDetailsComponent {
    private val classesStore = instanceKeeper.getStore {
        ClassDetailsStoreFactory(
            classDetailsDependencies = classDetailsDependencies,
            storeFactory = storeFactory,
            index = index,
        ).create()
    }
    override val state: Value<ClassDetailsStore.State> = classesStore.asValue()
}
