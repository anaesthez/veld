package com.nesterov.veld.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nesterov.veld.presentation.RaceComponent

@Composable
fun RaceScreen(component: RaceComponent) {
    val state by component.state.subscribeAsState()

    SpellScreenStateful()
}

@Composable
private fun SpellScreenStateful() {
   Column(
       modifier = Modifier.fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center,
   ) {
       Text("race")
   }
}
