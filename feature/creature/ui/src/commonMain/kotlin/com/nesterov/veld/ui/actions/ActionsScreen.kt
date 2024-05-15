package com.nesterov.veld.ui.actions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.design_system.ui.HeadedBlock
import com.nesterov.veld.design_system.ui.VeldItemsLazyRow
import com.nesterov.veld.presentation.creature.model.ActionDamagePresentationModel
import com.nesterov.veld.presentation.creature.model.ActionPresentationModel
import com.nesterov.veld.presentation.creature.model.ActionType
import com.nesterov.veld.presentation.creature.model.CreatureActionPresentationModel
import com.nesterov.veld.presentation.creature.model.DifficultyPresentationModel
import com.nesterov.veld.ui.getHeaderColorByActionType
import com.nesterov.veld.ui.getTitleByActionType
import com.nesterov.veld.сore.design_system.strings.DesignStrings
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ActionsScreen(
    actionsMap: Map<ActionType, List<CreatureActionPresentationModel>>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        item {
            Spacer(Modifier.height(16.dp))
        }
        item {
            actionsMap.forEach { entry ->
                CreatureActions(
                    creatureActions = entry.value,
                    title = entry.key.getTitleByActionType(),
                    headerColor = entry.key.getHeaderColorByActionType()
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun CreatureActions(
    creatureActions: List<CreatureActionPresentationModel>,
    headerColor: Color,
    title: String,
) {
    HeadedBlock(
        modifier = Modifier.fillMaxSize(),
        headerText = title,
        fontSize = 22.sp,
        color = headerColor,
        textAlign = TextAlign.Center,
    ) {
        creatureActions.forEach { creatureAction ->
            CreatureActionItem(creatureAction)
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun CreatureActionItem(
    creatureAction: CreatureActionPresentationModel,
) {
    Text(
        text = creatureAction.name,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        maxLines = 1,
    )
    if (creatureAction.attackBonus != 0) {
        InfoText(
            text = DesignStrings.creature_attack_bonus.format(
                bonus = creatureAction.attackBonus.toString()
            )
        )
    }
    if (creatureAction.usage.type.isNotBlank()) {
        InfoText(text = DesignStrings.creature_usage_type.format(usageType = creatureAction.usage.type))
    }
    if (creatureAction.usage.times != 0) {
        InfoText(text = DesignStrings.creature_usage_times.format(times = creatureAction.usage.times.toString()))
    }
    if (creatureAction.usage.restTypes.isNotEmpty()) {
        InfoText(text = DesignStrings.creature_rest_type.format(restType = creatureAction.usage.restTypes.joinToString()))
    }
    Text(text = creatureAction.desc)
    if (creatureAction.actions.isNotEmpty()) {
        VeldItemsLazyRow(itemsList = creatureAction.actions.toImmutableList()) {
            ActionListItem(it)
        }
    }
    if (creatureAction.damage.isNotEmpty()) {
        Column {
            Text(text = DesignStrings.creature_damage_header_title, fontWeight = FontWeight.Medium)
            VeldItemsLazyRow(itemsList = creatureAction.damage.toImmutableList()) {
                DamageListItem(it)
            }
        }
    }
    if (creatureAction.difficultyClass.difficultyValue != 0) {
        ActionDifficulty(
            difficulty = creatureAction.difficultyClass,
        )
    }
}

@Composable
private fun InfoText(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        maxLines = 1,
    )
}

@Composable
private fun ActionListItem(action: ActionPresentationModel) {
    ListItem(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = true, onClick = { }),
        leadingContent = {
            if (action.count != 0) {
                ActionCount(count = action.count)
            }
        },
        headlineContent = {
            Column {
                Text(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    maxLines = 1,
                    color = colors.textColorSecondary,
                    text = action.name,
                )
                Text(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    maxLines = 1,
                    color = colors.textColorSecondary,
                    text = action.type,
                )
            }
        },
        colors = ListItemDefaults.colors(containerColor = colors.materialColors.background)
    )
}

@Composable
private fun ActionDifficulty(difficulty: DifficultyPresentationModel) {
    Column {
        Text(text = DesignStrings.creature_difficulty_header_title, fontWeight = FontWeight.Medium)
        Text(text = DesignStrings.creature_difficulty_success_type.format(successType = difficulty.successType))
        Spacer(modifier = Modifier.height(4.dp))
        Card(modifier = Modifier.wrapContentSize()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = difficulty.difficultyValue.toString(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = difficulty.type.name,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun DamageListItem(damage: ActionDamagePresentationModel) {
    ListItem(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = true, onClick = { }),
        leadingContent = {
            DamageDice(dice = damage.damageDice)
        },
        headlineContent = {
            Text(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                maxLines = 1,
                color = colors.textColorSecondary,
                text = damage.damageType.name,
            )
        },
        colors = ListItemDefaults.colors(containerColor = colors.materialColors.background)
    )
}

@Composable
private fun ActionCount(modifier: Modifier = Modifier, count: Int) {
    Box(
        modifier = modifier
            .size(25.dp)
            .background(colors.textColorPrimary, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            color = colors.textColorSecondary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            text = count.toString(),
        )
    }
}

@Composable
private fun DamageDice(modifier: Modifier = Modifier, dice: String) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .background(colors.textColorPrimary, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            color = colors.textColorSecondary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            text = dice,
        )
    }
}

