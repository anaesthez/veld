package com.nesterov.veld.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    val onObtainEvent: (ClassesComponent.Event) -> Unit = remember {
        { event ->
            component.onObtainEvent(event)
        }
    }

    ClassesScreenStateful(
        charClassesList = state.charClassesList,
        lazyGridState = lazyGridState,
        onObtainEvent = onObtainEvent,
    )
}

@Composable
private fun ClassesScreenStateful(
    charClassesList: ImmutableList<CharacterClassPresentationModel>,
    lazyGridState: LazyGridState,
    onObtainEvent: (ClassesComponent.Event) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = lazyGridState,
        columns = GridCells.Fixed(GRID_CELLS),
    ) {
        repeat(GRID_CELLS) {
            item {
                Spacer(Modifier)
            }
        }
        items(charClassesList) { charClass ->
            CharClassItem(
                charClassName = charClass.name,
                onClassClick = {
                    onObtainEvent(ClassesComponent.Event.OnClassClick(charClass.char.index))
                }
            )
        }
    }
}

@Composable
private fun CharClassItem(
    charClassName: String,
    onClassClick: () -> Unit,
) {
    ListItem(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClassClick),
        headlineContent = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = charClassName,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                maxLines = 1,
                color = colors.textColorPrimary,
            )
        },
        colors = ListItemDefaults.colors(containerColor = Color(0xFF8F94FF))
    )
}

