package com.nesterov.veld.ui

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nesterov.veld.presentation.ClassDetailsComponent

@Composable
fun ClassDetailsScreen(component: ClassDetailsComponent) {
    val state by component.state.subscribeAsState()
    val lazyGridState = rememberLazyGridState()

    ClassDetailsScreenStateful(

    )
}

@Composable
private fun ClassDetailsScreenStateful(

) {

}


