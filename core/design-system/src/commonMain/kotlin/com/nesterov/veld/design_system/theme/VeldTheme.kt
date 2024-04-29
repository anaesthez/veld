package com.nesterov.veld.design_system.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

object VeldTheme {
    val colors
        @Composable
        @ReadOnlyComposable
        get() = veldColors.current
}

@Composable
fun VeldTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorTheme = if (useDarkTheme) veldDark() else veldLight()
    val colors = remember { colorTheme.copy() }
    colors.update(colorTheme)
    CompositionLocalProvider(
        veldColors provides colors,
    ) {
        MaterialTheme(
            colorScheme = colors.materialColors,
            content = {
                Surface(
                    modifier = Modifier.background(VeldTheme.colors.materialColors.surface),
                    content = content,
                )
            }
        )
    }
}
