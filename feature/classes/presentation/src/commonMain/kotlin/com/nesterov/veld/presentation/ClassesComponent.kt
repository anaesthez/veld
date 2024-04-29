package com.nesterov.veld.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.CharClass
import com.nesterov.veld.common.base.BaseComponent
import com.nesterov.veld.model.CharacterClassPresentationModel

sealed interface ClassesComponent {
    val state: Value<ClassesStore.State>
}

class ClassesComponentImpl(
    storeFactory: StoreFactory,
    componentContext: ComponentContext,
): BaseComponent(componentContext), ClassesComponent {
    private val classesStore = instanceKeeper.getStore {
        ClassesStoreFactory(
            storeFactory = storeFactory,
            characterClassesList = characterClassesList,
        ).create()
    }
    override val state: Value<ClassesStore.State> = classesStore.asValue()

    companion object {
        val characterClassesList = CharClass.entries.map {
            CharacterClassPresentationModel(
                char = it,
                name = it.index,
            )
        }
    }
}
