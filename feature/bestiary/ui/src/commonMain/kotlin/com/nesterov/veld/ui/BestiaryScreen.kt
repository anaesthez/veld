package com.nesterov.veld.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nesterov.veld.design_system.ui.VeldFailureScreen
import com.nesterov.veld.design_system.ui.VeldProgressBar
import com.nesterov.veld.presentation.BestiaryComponent
import com.nesterov.veld.presentation.BestiaryStore

@Composable
fun BestiaryScreen(component: BestiaryComponent) {
    val state by component.state.subscribeAsState()

    val onObtainEvent: (BestiaryComponent.Event) -> Unit = remember {
        { event ->
            component.onObtainEvent(event)
        }
    }
    when (state.screenState) {
        BestiaryStore.ScreenState.Failure -> VeldFailureScreen(
            errorText = "",
            onRetryClick = {
                onObtainEvent(BestiaryComponent.Event.OnRetryClick)
            }
        )

        BestiaryStore.ScreenState.Loading -> VeldProgressBar()
        is BestiaryStore.ScreenState.Success -> {
            val result = (state.screenState as BestiaryStore.ScreenState.Success)
            println(result.creatures)
            BestiaryScreenStateful()
        }
    }
}

@Composable
private fun BestiaryScreenStateful() {
   Column(
       modifier = Modifier.fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center,
   ) {
       Text("bestiary")
   }
}
