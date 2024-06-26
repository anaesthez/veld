package com.nesterov.veld.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.nesterov.veld.design_system.theme.VeldIcons
import com.nesterov.veld.design_system.theme.VeldTheme
import com.nesterov.veld.design_system.ui.VeldFailureScreen
import com.nesterov.veld.design_system.ui.VeldProgressBar
import com.nesterov.veld.presentation.BestiaryComponent
import com.nesterov.veld.presentation.BestiaryStore
import com.nesterov.veld.presentation.model.CreatureMemoryStatus
import com.nesterov.veld.presentation.model.CreaturePresentationModel
import io.github.skeptick.libres.compose.painterResource
import kotlinx.collections.immutable.ImmutableList

@Composable
fun BestiaryScreen(component: BestiaryComponent) {
    val state by component.state.subscribeAsState()
    val lazyListState = rememberLazyListState()

    val onObtainEvent: (BestiaryComponent.Event) -> Unit = remember {
        { event ->
            component.onObtainEvent(event)
        }
    }

    when (val bestiary = state.screenState) {
        BestiaryStore.ScreenState.Failure -> VeldFailureScreen(
            errorText = "",
            onRetryClick = {
                onObtainEvent(BestiaryComponent.Event.OnRetryClick)
            }
        )

        BestiaryStore.ScreenState.Loading -> VeldProgressBar()
        is BestiaryStore.ScreenState.Success -> BestiaryScreenStateful(
            lazyListState = lazyListState,
            creatures = bestiary.creatures,
            onObtainEvent = onObtainEvent,
        )
    }
}

@Composable
private fun BestiaryScreenStateful(
    lazyListState: LazyListState,
    creatures: ImmutableList<CreaturePresentationModel>,
    onObtainEvent: (BestiaryComponent.Event) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 12.dp),
        state = lazyListState,
    ) {
        items(creatures) { creature ->
            Spacer(modifier = Modifier.height(8.dp))
            CreatureListItem(
                creatureName = creature.name,
                memoryStatus = creature.status,
                onCreatureClick = {
                    onObtainEvent(BestiaryComponent.Event.OnCreatureClick(creature.index))
                },
                onDeleteClick = {
                    onObtainEvent(BestiaryComponent.Event.OnDeleteClick(creature.index))
                },
                onAddClick = {
                    onObtainEvent(BestiaryComponent.Event.OnAddClick(creature))
                }
            )
        }
    }
}

@Composable
private fun CreatureListItem(
    creatureName: String,
    memoryStatus: CreatureMemoryStatus,
    onCreatureClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onAddClick: () -> Unit,
) {
    ListItem(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = true, onClick = onCreatureClick),
        headlineContent = {
            Text(
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                maxLines = 1,
                color = VeldTheme.colors.textColorPrimary,
                text = creatureName,
            )
        },
        trailingContent = {
            // MemoryStatusIconButton(memoryStatus, onDeleteClick, onAddClick) TODO("add saving")
        },
        colors = ListItemDefaults.colors(containerColor = Color(0xFF8F94FF))
    )
}

@Composable
private fun MemoryStatusIconButton(
    status: CreatureMemoryStatus,
    onDeleteClick: () -> Unit,
    onAddClick: () -> Unit
) {
    val (iconResource, clickAction) = when (status) {
        CreatureMemoryStatus.LOCAL -> VeldIcons.delete to onDeleteClick
        CreatureMemoryStatus.REMOTE -> VeldIcons.add to onAddClick
    }

    IconButton(onClick = clickAction) {
        Icon(
            painter = painterResource(iconResource),
            contentDescription = null
        )
    }
}