package com.nesterov.veld.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.model.CharacterClassPresentationModel
import com.nesterov.veld.presentation.ClassesComponent
import kotlinx.collections.immutable.ImmutableList

private const val GRID_CELLS = 2

@Composable
fun ClassesScreen(component: ClassesComponent) {
    val state by component.state.subscribeAsState()
    val lazyGridState = rememberLazyGridState()

    ClassesScreenStateful(
        charClassesList = state.charClassesList,
        lazyGridState = lazyGridState,
    )
}

@Composable
private fun ClassesScreenStateful(
    charClassesList: ImmutableList<CharacterClassPresentationModel>,
    lazyGridState: LazyGridState,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = lazyGridState,
        columns = GridCells.Fixed(GRID_CELLS),
    ) {
        items(charClassesList) { charClass ->
            CharClassItem(
                charClassName = charClass.name,
            )
        }
    }
}

@Composable
private fun CharClassItem(
    charClassName: String
) {
    ListItem(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp)),
        headlineContent = {
            Text(
                text = charClassName,
                color = colors.textColorPrimary,
            )
        },
        colors = ListItemDefaults.colors(containerColor = Color(0xFF8F94FF))
    )
}

