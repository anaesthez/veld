package ru.nesterov.veld.hub

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.pages.Pages
import com.arkivanov.decompose.extensions.compose.pages.PagesScrollAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nesterov.veld.common_ui.VieldSearchBar
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import ru.nesterov.veld.hub.model.Page
import ru.nesterov.veld.hub.model.SelectablePageUiModel
import ru.nesterov.veld.hub.utils.imgByPage
import ru.nesterov.veld.hub.utils.resByPage
import com.nesterov.veld.ui.BackstoryScreen
import com.nesterov.veld.ui.ClassesScreen
import com.nesterov.veld.ui.ItemScreen
import com.nesterov.veld.ui.RaceScreen
import com.nesterov.veld.ui.SpellScreen
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalDecomposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun HubRootScreen(component: HubRootComponent) {
    val state by component.state.subscribeAsState()
    val pages by component.pages.subscribeAsState()
    val lazyPagesListState = rememberLazyListState()

    val onObtainEvent: (HubRootComponent.Event) -> Unit = remember {
        { event ->
            component.onObtainEvent(event)
        }
    }

    LaunchedEffect(pages) {
        lazyPagesListState.animateScrollToItem(pages.selectedIndex)
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(24.dp))
        Column {
            HubHeaderTitle(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            )
            Spacer(Modifier.size(16.dp))
            HubSearchBar(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                query = state.query,
                placeholderText = "A spell, feature or race...",
                onQueryChange = {
                    onObtainEvent(HubRootComponent.Event.OnInputQuery(it))
                },
                onSearch = {
                    onObtainEvent(HubRootComponent.Event.OnSearchQuery)
                },
                onFilter = {
                    onObtainEvent(HubRootComponent.Event.NavigateFilter)
                },
            )
        }
        Spacer(Modifier.size(16.dp))
        HubPagesLazyRow(
            lazyPagesListState = lazyPagesListState,
            pages = state.pages,
            onSelectPage = {
                onObtainEvent(HubRootComponent.Event.OnSelectPage(it))
            },
        )
        Spacer(Modifier.size(8.dp))
        HorizontalDivider(color = colors.materialColors.surfaceVariant)
        Spacer(Modifier.size(8.dp))
        Pages(
            pages = component.pages,
            onPageSelected = {
                onObtainEvent(HubRootComponent.Event.OnSelectPage(it))
            },
            scrollAnimation = PagesScrollAnimation.Default,
        ) { _, page ->
            when (page) {
                is HubRootComponent.Pages.Classes -> ClassesScreen(
                    component = page.component
                )

                is HubRootComponent.Pages.Items -> ItemScreen(
                    component = page.component
                )

                is HubRootComponent.Pages.Spells -> SpellScreen(
                    component = page.component,
                )

                is HubRootComponent.Pages.Race -> RaceScreen(
                    component = page.component,
                )

                is HubRootComponent.Pages.Backstory -> BackstoryScreen(
                    component = page.component,
                )
            }
        }
    }
}

@Composable
private fun HubHeaderTitle(modifier: Modifier = Modifier) {
    Column {
        Spacer(Modifier.size(24.dp))
        Text(
            modifier = modifier,
            text = "What are you searching for,",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.scrim,
        )
        Spacer(Modifier.size(12.dp))
        Text(
            modifier = modifier,
            text = "adventurer?",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.scrim,
        )
    }
}

@Composable
private fun HubSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    placeholderText: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onFilter: () -> Unit,
) {
    VieldSearchBar(
        modifier = modifier,
        placeholderText = placeholderText,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        query = query,
        leadingIcon = {
            IconButton(
                onClick = onFilter,
                content = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                    )
                }
            )
        },
        trailingIcon = {
            IconButton(
                onClick = onSearch,
                content = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                }
            )
        },
    )
}

@Composable
private fun HubPagesLazyRow(
    modifier: Modifier = Modifier,
    lazyPagesListState: LazyListState,
    pages: ImmutableList<SelectablePageUiModel>,
    onSelectPage: (Int) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        state = lazyPagesListState,
        content = {
            items(pages) { page ->
                SelectablePageCard(
                    isSelected = page.isSelected,
                    pageType = page.pageType,
                    onSelectPage = onSelectPage,
                )
            }
        }
    )
}

@Composable
private fun SelectablePageCard(
    modifier: Modifier = Modifier,
    pageType: Page,
    isSelected: Boolean,
    onSelectPage: (Int) -> Unit,
) {
    val elevation by animateDpAsState(if (isSelected) 16.dp else 0.dp)
    Box(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .graphicsLayer { if (isSelected) shadowElevation = elevation.toPx() }
            .clip(CardDefaults.shape)
            .background(
                color = Color.LightGray,
                shape = if (isSelected) CardDefaults.elevatedShape else CardDefaults.shape
            )
            .clickable {
                onSelectPage(pageType.ordinal)
            }
    ) {
        Row(
            modifier = Modifier
                .width(160.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.alpha(if (isSelected) 1f else 0.3f),
                text = pageType.resByPage(),
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = colors.textColorPrimary,
            )
            Spacer(Modifier.size(8.dp))
            if (isSelected) {
                Image(
                    modifier = Modifier.size(50.dp),
                    painter = pageType.imgByPage(),
                    contentDescription = null,
                )
            } else {
                Box(modifier = Modifier.size(50.dp))
            }
        }
    }
}