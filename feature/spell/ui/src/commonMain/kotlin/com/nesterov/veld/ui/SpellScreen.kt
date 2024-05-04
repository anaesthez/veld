package com.nesterov.veld.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.design_system.ui.VeldFailureScreen
import com.nesterov.veld.design_system.ui.VeldProgressBar
import com.nesterov.veld.presentation.SpellComponent
import com.nesterov.veld.presentation.SpellStore
import com.nesterov.veld.presentation.model.SpellPresentationModel
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SpellScreen(component: SpellComponent) {
    val state by component.state.subscribeAsState()
    val lazyPagesListState = rememberLazyListState()

    val onObtainEvent: (SpellComponent.Event) -> Unit = remember {
        { event ->
            component.onObtainEvent(event)
        }
    }

    when(state.screenState) {
        is SpellStore.ScreenState.Failure -> VeldFailureScreen(
            errorText = "",
            onRetryClick = {

            }
        )
        is SpellStore.ScreenState.Loading -> VeldProgressBar()
        is SpellStore.ScreenState.Success -> SpellScreenStateful(
            spellList = (state.screenState as SpellStore.ScreenState.Success).spellsList,
            scrollState = lazyPagesListState,
            onSpellClick = { spell -> onObtainEvent(SpellComponent.Event.OnSpellClick(spell)) },
        )
    }
}

@Composable
private fun SpellScreenStateful(
    modifier: Modifier = Modifier,
    spellList: ImmutableList<SpellPresentationModel>,
    scrollState: LazyListState,
    onSpellClick: (SpellPresentationModel) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = scrollState,
    ) {
        items(spellList) { spell ->
            SpellNewCard(
                level = spell.level.toString(),
                name = spell.name,
                onSpellClick = { onSpellClick(spell) },
            )
        }
    }
}
@Composable
private fun SpellNewCard(
    modifier: Modifier = Modifier,
    onSpellClick: () -> Unit,
    name: String,
    level: String,
) {
    ListItem(
        modifier = modifier
            .semantics(mergeDescendants = true) { }
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = true, onClick = onSpellClick),
        leadingContent = {
            SpellCircle(
                circle = level,
            )
        },
        headlineContent = {
            SpellName(
                level = name,
            )
        },
        colors = ListItemDefaults.colors(containerColor = Color(0xFF8F94FF))
    )
}

@Composable
private fun SpellCircle(modifier: Modifier = Modifier, circle: String) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(25.dp)
                .background(colors.textColorPrimary, RoundedCornerShape(8.dp))
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                color = colors.textColorSecondary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                text = circle,
            )
        }
    }
}

@Composable
private fun SpellName(modifier: Modifier = Modifier, level: String) {
    Text(
        modifier = modifier,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        maxLines = 1,
        color = colors.textColorPrimary,
        text = level,
    )
}