package com.nesterov.veld.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.presentation.creature.CreatureComponent
import com.nesterov.veld.presentation.creature.model.ActionType
import com.nesterov.veld.presentation.creature.model.MovingType
import com.nesterov.veld.presentation.creature.model.Stat
import com.nesterov.veld.сore.design_system.strings.DesignStrings

fun Stat.getTitleByStat(multiplier: String): String =
    when (this) {
        Stat.CHARISMA -> DesignStrings.charisma.format(multiplier = multiplier)
        Stat.WISDOM -> DesignStrings.wisdom.format(multiplier = multiplier)
        Stat.STRENGTH -> DesignStrings.strength.format(multiplier = multiplier)
        Stat.DEXTERITY -> DesignStrings.dexterity.format(multiplier = multiplier)
        Stat.CONSTITUTION -> DesignStrings.constitution.format(multiplier = multiplier)
        Stat.INTELLIGENCE -> DesignStrings.intelligence.format(multiplier = multiplier)
    }

@Composable
fun Stat.getColorByStat(): Color =
    when (this) {
        Stat.CHARISMA -> colors.charisma
        Stat.WISDOM -> colors.wisdom
        Stat.STRENGTH -> colors.strength
        Stat.DEXTERITY -> colors.dexterity
        Stat.CONSTITUTION -> colors.constitution
        Stat.INTELLIGENCE -> colors.intelligence
    }

fun MovingType.getTitleByMovingType(speed: String): String =
    when (this) {
        MovingType.WALK -> DesignStrings.movement_walk.format(speed)
        MovingType.CLIMBING -> DesignStrings.movement_climbing.format(speed)
        MovingType.BURROW -> DesignStrings.movement_burrowing.format(speed)
        MovingType.SWIM -> DesignStrings.movement_swimming.format(speed)
    }

fun ActionType.getTitleByActionType(): String =
    when (this) {
        ActionType.COMMON -> DesignStrings.creature_info_action_default_text
        ActionType.SPECIAL -> DesignStrings.creature_info_action_special_text
        ActionType.LEGENDARY -> DesignStrings.creature_info_action_legendary_text
    }

@Composable
fun ActionType.getHeaderColorByActionType(): Color =
    when (this) {
        ActionType.COMMON -> colors.dexterity
        ActionType.SPECIAL -> colors.charisma
        ActionType.LEGENDARY -> colors.strength
    }

fun Int.getMultiplierByStat(): String =
    when (this) {
        1 -> "-5"
        in 2..3 -> "-4"
        in 4..5 -> "-3"
        in 6..7 -> "-2"
        in 8..9 -> "-1"
        in 10..11 -> "0"
        in 12..13 -> "+1"
        in 14..15 -> "+2"
        in 16..17 -> "+3"
        in 18..19 -> "+4"
        in 20..21 -> "+5"
        in 22..23 -> "+6"
        in 24..25 -> "+7"
        in 26..27 -> "+8"
        in 28..29 -> "+9"
        30 -> "+10"
        else -> ""
    }

fun CreatureComponent.Pages.getTitleByPage(): String =
    when (this) {
        is CreatureComponent.Pages.Actions -> DesignStrings.creature_info_tabs_actions_text
        is CreatureComponent.Pages.Info -> DesignStrings.creature_info_tabs_info_text
        is CreatureComponent.Pages.Stats -> DesignStrings.creature_info_tabs_stats_text
    }