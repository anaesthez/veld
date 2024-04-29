package com.nesterov.veld.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nesterov.veld.presentation.ClassesComponent

@Composable
fun ClassesScreen(component: ClassesComponent) {
    val state by component.state.subscribeAsState()

    ClassesScreenStateful(
    )
}

@Composable
private fun ClassesScreenStateful(
) {

}

