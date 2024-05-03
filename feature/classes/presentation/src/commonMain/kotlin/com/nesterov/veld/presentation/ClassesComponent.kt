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

    fun onObtainEvent(event: Event)

    sealed interface Event {
        data class OnClassClick(val index: String) : Event
    }

    sealed interface Action {
        data class NavigateClassDetails(val index: String) : Action
    }
}

class ClassesComponentImpl(
    storeFactory: StoreFactory,
    componentContext: ComponentContext,
    private val action: (ClassesComponent.Action) -> Unit,
): BaseComponent(componentContext), ClassesComponent {
    private val classesStore = instanceKeeper.getStore {
        ClassesStoreFactory(
            storeFactory = storeFactory,
            characterClassesList = characterClassesList,
        ).create()
    }
    override val state: Value<ClassesStore.State> = classesStore.asValue()

    override fun onObtainEvent(event: ClassesComponent.Event) =
        when (event) {
            is ClassesComponent.Event.OnClassClick -> {
                action(ClassesComponent.Action.NavigateClassDetails(event.index))
            }
        }

    companion object {
        val characterClassesList = CharClass.entries.map {
            CharacterClassPresentationModel(
                char = it,
                name = it.index,
            )
        }
    }
}
