package com.nesterov.veld.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.pages.Pages
import com.arkivanov.decompose.extensions.compose.pages.PagesScrollAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value
import com.nesterov.veld.design_system.ui.VeldAppBar
import com.nesterov.veld.design_system.ui.VeldFailureScreen
import com.nesterov.veld.design_system.ui.VeldProgressBar
import com.nesterov.veld.presentation.creature.CreatureComponent
import com.nesterov.veld.presentation.creature.CreatureStore
import com.nesterov.veld.presentation.creature.model.ActionType
import com.nesterov.veld.presentation.creature.model.ArmorPresentationModel
import com.nesterov.veld.presentation.creature.model.CreatureActionPresentationModel
import com.nesterov.veld.presentation.creature.model.MovingType
import com.nesterov.veld.presentation.creature.model.Stat
import com.nesterov.veld.ui.actions.ActionsScreen
import com.nesterov.veld.ui.info.InfoScreen
import com.nesterov.veld.ui.stats.StatsScreen
import kotlinx.collections.immutable.ImmutableMap

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun CreatureScreen(component: CreatureComponent) {
    val state by component.state.subscribeAsState()

    val onObtainEvent: (CreatureComponent.Event) -> Unit = remember {
        { event ->
            component.onObtainEvent(event)
        }
    }

    when (state.screenState) {
        CreatureStore.ScreenState.Failure -> VeldFailureScreen(
            errorText = "",
            onRetryClick = {
                onObtainEvent(CreatureComponent.Event.OnRetryClick)
            }
        )

        CreatureStore.ScreenState.Loading -> VeldProgressBar()
        is CreatureStore.ScreenState.Success -> {
            val result = (state.screenState as CreatureStore.ScreenState.Success)
            CreatureScreenStateful(
                pages = component.pages,
                name = result.creature.name,
                headerImageUrl = result.creature.imageUrl,
                hitPoints = result.creature.hitPoints,
                hitDice = result.creature.hitDice,
                challengeRating = result.creature.challengeRating,
                xpGain = result.creature.xpGain,
                hitRolls = result.creature.hitPointsRoll,
                proficiencyBonus = result.creature.proficiencyBonus,
                actionsMap = result.creature.actionsMap,
                statsMap = result.creature.stats.statsMap,
                speedStats = result.creature.speed.movementsMap,
                creatureArmor = result.creature.armor,
                onBackClick = {
                    onObtainEvent(CreatureComponent.Event.OnBackClick)
                },
                onPageSelected = {
                    onObtainEvent(CreatureComponent.Event.OnPageSelected(it))
                }
            )
        }
    }
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
private fun CreatureScreenStateful(
    name: String,
    headerImageUrl: String,
    hitPoints: Int,
    xpGain: Int,
    challengeRating: Float,
    hitDice: String,
    hitRolls: String,
    proficiencyBonus: Int,
    statsMap: ImmutableMap<Stat, Int>,
    speedStats: Map<MovingType, String>,
    pages: Value<ChildPages<*, CreatureComponent.Pages>>,
    actionsMap: Map<ActionType, List<CreatureActionPresentationModel>>,
    creatureArmor: List<ArmorPresentationModel>,
    onBackClick: () -> Unit,
    onPageSelected: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        VeldAppBar(
            titleText = name,
            onBackClick = onBackClick,
        )
        CreaturePages(
            pages = pages,
            headerImageUrl = headerImageUrl,
            hitPoints = hitPoints,
            xpGain = xpGain,
            challengeRating = challengeRating,
            hitDice = hitDice,
            hitRolls = hitRolls,
            proficiencyBonus = proficiencyBonus,
            statsMap = statsMap,
            speedStats = speedStats,
            actionsMap = actionsMap,
            creatureArmor = creatureArmor,
            onPageSelected = onPageSelected,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalDecomposeApi::class)
@Composable
private fun CreaturePages(
    pages: Value<ChildPages<*, CreatureComponent.Pages>>,
    headerImageUrl: String,
    hitPoints: Int,
    xpGain: Int,
    challengeRating: Float,
    hitDice: String,
    hitRolls: String,
    proficiencyBonus: Int,
    statsMap: ImmutableMap<Stat, Int>,
    speedStats: Map<MovingType, String>,
    creatureArmor: List<ArmorPresentationModel>,
    actionsMap: Map<ActionType, List<CreatureActionPresentationModel>>,
    onPageSelected: (Int) -> Unit,
) {
    val observedPage by pages.subscribeAsState()
    val selectedIndex by derivedStateOf { observedPage.selectedIndex }
    TabRow(
        selectedTabIndex = selectedIndex,
    ) {
        pages.value.items.forEachIndexed { compositionIndex, page ->
            Tab(
                selected = selectedIndex == compositionIndex,
                onClick = { onPageSelected(compositionIndex) },
            ) {
                Text(
                    text = page.instance?.getTitleByPage().orEmpty()
                )
            }
        }
    }
    Pages(
        pages = pages,
        onPageSelected = onPageSelected,
        scrollAnimation = PagesScrollAnimation.Default,
    ) { _, page ->
        when (page) {
            is CreatureComponent.Pages.Actions -> {
                ActionsScreen(
                    actionsMap = actionsMap,
                )
            }

            is CreatureComponent.Pages.Info -> InfoScreen(

            )

            is CreatureComponent.Pages.Stats -> StatsScreen(
                headerImageUrl = headerImageUrl,
                hitPoints = hitPoints,
                xpGain = xpGain,
                challengeRating = challengeRating,
                hitDice = hitDice,
                hitRolls = hitRolls,
                proficiencyBonus = proficiencyBonus,
                statsMap = statsMap,
                speedStats = speedStats,
                creatureArmor = creatureArmor,
            )
        }
    }
}

private fun CreatureComponent.Pages.getTitleByPage(): String =
    when (this) {
        is CreatureComponent.Pages.Actions -> "Actions"
        is CreatureComponent.Pages.Info -> "Info"
        is CreatureComponent.Pages.Stats -> "Stats"
    }