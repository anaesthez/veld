package com.nesterov.veld.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.presentation.creature.model.ActionType
import com.nesterov.veld.presentation.creature.model.MovingType
import com.nesterov.veld.presentation.creature.model.Stat
import com.nesterov.veld.сore.design_system.strings.DesignStrings

fun Stat.getTitleByStat(): String =
    when (this) {
        Stat.CHARISMA -> DesignStrings.charisma
        Stat.WISDOM -> DesignStrings.wisdom
        Stat.STRENGTH -> DesignStrings.strength
        Stat.DEXTERITY -> DesignStrings.dexterity
        Stat.CONSTITUTION -> DesignStrings.constitution
        Stat.INTELLIGENCE -> DesignStrings.intelligence
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
        ActionType.COMMON -> "Default"
        ActionType.SPECIAL -> "Special abilities"
        ActionType.LEGENDARY -> "Legendary"
    }

@Composable
fun ActionType.getHeaderColorByActionType(): Color =
    when (this) {
        ActionType.COMMON -> colors.dexterity
        ActionType.SPECIAL -> colors.charisma
        ActionType.LEGENDARY -> colors.strength
    }