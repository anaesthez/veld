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
import com.nesterov.veld.presentation.creature.model.CreatureProficiencyPresentationModel
import com.nesterov.veld.presentation.creature.model.MovingType
import com.nesterov.veld.presentation.creature.model.Stat
import com.nesterov.veld.ui.actions.ActionsScreen
import com.nesterov.veld.ui.info.InfoScreen
import com.nesterov.veld.ui.stats.StatsScreen
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap

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
            with((state.screenState as CreatureStore.ScreenState.Success).creature) {
                CreatureScreenStateful(
                    pages = component.pages,
                    name = name,
                    headerImageUrl = imageUrl,
                    hitPoints = hitPoints,
                    hitDice = hitDice,
                    challengeRating = challengeRating,
                    xpGain = xpGain,
                    size = size,
                    type = type,
                    subtype = subtype,
                    languages = languages,
                    passivePerception = sense.passivePerception,
                    tremorSense = sense.tremorSense,
                    blindSight = sense.blindSight,
                    darkVision = sense.darkVision,
                    trueSight = sense.trueSight,
                    alignments = alignments,
                    description = description,
                    hitRolls = hitPointsRoll,
                    proficiencyBonus = proficiencyBonus,
                    statsMap = stats.statsMap,
                    speedStats = speed.movementsMap,
                    proficiencies = proficiencies.toImmutableList(),
                    actionsMap = actionsMap.toImmutableMap(),
                    creatureArmor = armor.toImmutableList(),
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
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
private fun CreatureScreenStateful(
    pages: Value<ChildPages<*, CreatureComponent.Pages>>,
    name: String,
    headerImageUrl: String,
    hitPoints: Int,
    xpGain: Int,
    size: String,
    type: String,
    subtype: String,
    languages: String,
    alignments: String,
    description: String,
    challengeRating: Float,
    hitDice: String,
    hitRolls: String,
    proficiencyBonus: Int,
    onBackClick: () -> Unit,
    onPageSelected: (Int) -> Unit,
    passivePerception: Int,
    tremorSense: String,
    blindSight: String,
    darkVision: String,
    trueSight: String,
    statsMap: ImmutableMap<Stat, Int>,
    speedStats: ImmutableMap<MovingType, String>,
    actionsMap: ImmutableMap<ActionType, List<CreatureActionPresentationModel>>,
    proficiencies: ImmutableList<CreatureProficiencyPresentationModel>,
    creatureArmor: ImmutableList<ArmorPresentationModel>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        VeldAppBar(
            titleText = name,
            onBackClick = onBackClick,
        )
        CreaturePages(
            size = size,
            type = type,
            pages = pages,
            xpGain = xpGain,
            hitDice = hitDice,
            subtype = subtype,
            statsMap = statsMap,
            hitRolls = hitRolls,
            trueSight = trueSight,
            hitPoints = hitPoints,
            languages = languages,
            blindSight = blindSight,
            darkVision = darkVision,
            alignments = alignments,
            actionsMap = actionsMap,
            speedStats = speedStats,
            description = description,
            tremorSense = tremorSense,
            creatureArmor = creatureArmor,
            proficiencies = proficiencies,
            onPageSelected = onPageSelected,
            headerImageUrl = headerImageUrl,
            challengeRating = challengeRating,
            proficiencyBonus = proficiencyBonus,
            passivePerception = passivePerception,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalDecomposeApi::class)
@Composable
private fun CreaturePages(
    pages: Value<ChildPages<*, CreatureComponent.Pages>>,
    hitPoints: Int,
    xpGain: Int,
    hitDice: String,
    size: String,
    type: String,
    subtype: String,
    hitRolls: String,
    languages: String,
    alignments: String,
    description: String,
    proficiencyBonus: Int,
    headerImageUrl: String,
    challengeRating: Float,
    onPageSelected: (Int) -> Unit,
    passivePerception: Int,
    tremorSense: String,
    blindSight: String,
    darkVision: String,
    trueSight: String,
    statsMap: ImmutableMap<Stat, Int>,
    speedStats: Map<MovingType, String>,
    creatureArmor: ImmutableList<ArmorPresentationModel>,
    proficiencies: ImmutableList<CreatureProficiencyPresentationModel>,
    actionsMap: ImmutableMap<ActionType, List<CreatureActionPresentationModel>>,
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
                size = size,
                type = type,
                passivePerception = passivePerception,
                tremorSense = tremorSense,
                blindSight = blindSight,
                darkVision = darkVision,
                trueSight = trueSight,
                subtype = subtype,
                languages = languages,
                alignments = alignments,
                description = description,
            )

            is CreatureComponent.Pages.Stats -> StatsScreen(
                headerImageUrl = headerImageUrl,
                hitPoints = hitPoints,
                xpGain = xpGain,
                challengeRating = challengeRating,
                proficiencies = proficiencies,
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