package com.nesterov.veld.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nesterov.veld.design_system.ui.VeldFailureScreen
import com.nesterov.veld.design_system.ui.VeldProgressBar
import com.nesterov.veld.presentation.CreatureComponent
import com.nesterov.veld.presentation.CreatureStore
import com.nesterov.veld.presentation.model.CreatureDetailsPresentationModel

@Composable
fun CreatureScreen(component: CreatureComponent) {
    val state by component.state.subscribeAsState()

    val onObtainEvent: (CreatureComponent.Event) -> Unit = remember {
        { event ->
            component.onObtainEvent(event)
        }
    }
    when (state.screenState) {
        CreatureStore.ScreenState.Failure -> VeldFailureScreen(
            errorText = "",
            onRetryClick = {
                onObtainEvent(CreatureComponent.Event.OnRetryClick)
            }
        )

        CreatureStore.ScreenState.Loading -> VeldProgressBar()
        is CreatureStore.ScreenState.Success -> {
            val result = (state.screenState as CreatureStore.ScreenState.Success)
            println(result.creature)
            BestiaryScreenStateful(
                creature = result.creature,
                onObtainEvent = onObtainEvent,
            )
        }
    }
}

@Composable
private fun BestiaryScreenStateful(
    creature: CreatureDetailsPresentationModel,
    onObtainEvent: (CreatureComponent.Event) -> Unit
) {
    println(creature)
}