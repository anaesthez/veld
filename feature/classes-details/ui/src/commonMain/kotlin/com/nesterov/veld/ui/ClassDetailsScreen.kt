package com.nesterov.veld.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nesterov.veld.presentation.ClassDetailsComponent
import com.nesterov.veld.presentation.ClassDetailsStore

@Composable
fun ClassDetailsScreen(component: ClassDetailsComponent) {
    val state by component.state.subscribeAsState()

    println(
        (state.screenState as? ClassDetailsStore.ScreenState.Success)?.classDetails
    )
    ClassDetailsScreenStateful(

    )
}

@Composable
private fun ClassDetailsScreenStateful(

) {

}


