package ru.nesterov.veld.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.nesterov.veld.ui.SpellDetailsScreen
import ru.nesterov.veld.hub.HubRootScreen

@Composable
fun RootScreen(component: RootComponent) {
    Children(
        stack = component.childStack,
        content = {
            when (val child = it.instance) {
                is RootComponent.Child.Hub -> HubRootScreen(component = child.component)
                is RootComponent.Child.SpellDetails -> SpellDetailsScreen(component = child.component)
            }
        }
    )
}