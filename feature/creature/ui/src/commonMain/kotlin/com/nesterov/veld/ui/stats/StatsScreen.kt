package com.nesterov.veld.ui.stats

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import com.nesterov.veld.common.getAsyncImageLoader
import com.nesterov.veld.design_system.theme.VeldTheme
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.design_system.ui.HeadedBlock
import com.nesterov.veld.design_system.ui.VeldListItem
import com.nesterov.veld.helpers.appendUrl
import com.nesterov.veld.presentation.creature.model.ArmorPresentationModel
import com.nesterov.veld.presentation.creature.model.CreatureProficiencyPresentationModel
import com.nesterov.veld.presentation.creature.model.MovingType
import com.nesterov.veld.presentation.creature.model.Stat
import com.nesterov.veld.ui.VeldStatIndicator
import com.nesterov.veld.ui.getColorByStat
import com.nesterov.veld.ui.getMultiplierByStat
import com.nesterov.veld.ui.getTitleByMovingType
import com.nesterov.veld.ui.getTitleByStat
import com.nesterov.veld.сore.design_system.strings.DesignStrings
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap

private const val MAX_HIT_POINTS = 400f
private const val MAX_XP = 30000f
private const val MAX_CHALLENGE_RATING = 25f
private const val MAX_STAT = 22f
private const val ITEMS_PER_ROW = 2

@Composable
fun StatsScreen(
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
    proficiencies: ImmutableList<CreatureProficiencyPresentationModel>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Spacer(Modifier.height(16.dp))
        }
        item {
            CreatureHeaderBlock(
                creatureImageUrl = headerImageUrl.appendUrl(),
                hitPoints = hitPoints,
                hitDice = hitDice,
                hitRolls = hitRolls,
                proficiencyBonus = proficiencyBonus,
                creatureArmor = creatureArmor,
            )
            Spacer(Modifier.height(16.dp))
            CreatureStatsBlock(
                statMap = statsMap,
            )
            Spacer(Modifier.height(16.dp))
            CreatureDiceAndBonusBlock(
                hitRolls = hitRolls,
                proficiencyBonus = proficiencyBonus,
            )
            if (proficiencies.isNotEmpty()) {
                Spacer(Modifier.height(16.dp))
                ProficienciesBlock(
                    proficiencies = proficiencies,
                )
            }
            Spacer(Modifier.height(24.dp))
            CreatureSpeedAndArmorBlock(
                speedStats = speedStats,
                creatureArmor = creatureArmor,
            )
            Spacer(Modifier.height(16.dp))
            CreatureExperienceGain(
                xpGain = xpGain,
            )
            Spacer(Modifier.height(16.dp))
            CreatureChallengeRating(
                challengeRating = challengeRating,
            )
        }
        item {
            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun ProficienciesBlock(
    proficiencies: ImmutableList<CreatureProficiencyPresentationModel>,
) {
    HeadedBlock(
        modifier = Modifier.padding(horizontal = 16.dp),
        headerText = DesignStrings.creature_prof_header_text
    ) {
        Column {
            proficiencies.chunked(ITEMS_PER_ROW).forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    rowItems.forEach { proficiency ->
                        VeldListItem(
                            title = proficiency.proficiency.name,
                            backgroundColor = colors.materialColors.primary,
                            onItemClick = { },
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 8.dp)
                        )
                    }
                    if (rowItems.size < ITEMS_PER_ROW) {
                        for (i in rowItems.size until ITEMS_PER_ROW) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CreatureHeaderBlock(
    modifier: Modifier = Modifier,
    hitPoints: Int,
    hitDice: String,
    hitRolls: String,
    proficiencyBonus: Int,
    creatureImageUrl: String,
    creatureArmor: List<ArmorPresentationModel>,
) {
    val armor = creatureArmor.firstOrNull()
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            model = creatureImageUrl,
            imageLoader = LocalPlatformContext.current.getAsyncImageLoader(),
            contentDescription = null,
        )
        Box(contentAlignment = Alignment.Center) {
            LinearProgressIndicator(
                modifier = Modifier
                    .width(200.dp)
                    .height(18.dp)
                    .clip(RoundedCornerShape(12.dp)),
                progress = { hitPoints.toFloat() / MAX_HIT_POINTS },
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp),
                text = DesignStrings.creature_hit_points.format(hp = hitPoints.toString()),
                color = colors.textColorPrimary,
                maxLines = 1,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
        if (armor != null) {
            Text(
                modifier = Modifier.width(200.dp),
                text = DesignStrings.creature_stats_armor_text.format(
                    armorValue = armor.value.toString(),
                ),
                maxLines = 1,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
private fun CreatureDiceAndBonusBlock(
    hitRolls: String,
    proficiencyBonus: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = DesignStrings.creature_hit_rolls.format(
                hitRolls = hitRolls
            ),
            maxLines = 1,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(Modifier.size(8.dp))
        Text(
            text = DesignStrings.creature_prof_bonus.format(
                bonus = proficiencyBonus.toString()
            ),
            maxLines = 1,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
private fun CreatureStatsBlock(
    statMap: ImmutableMap<Stat, Int>,
) {
    statMap.forEach { entry ->
        val indicator = entry.value
        Spacer(Modifier.height(4.dp))
        CreatureStatIndicator(
            indicatorTitle = entry.key.getTitleByStat(indicator.getMultiplierByStat()),
            color = entry.key.getColorByStat(),
            indicatorValue = indicator,
        )
        Spacer(Modifier.height(4.dp))
    }
}

@Composable
private fun CreatureStatIndicator(
    indicatorTitle: String,
    indicatorValue: Int,
    color: Color,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.weight(0.1f))
        Text(
            modifier = Modifier.weight(0.2f),
            text = indicatorTitle,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.width(4.dp))
        VeldStatIndicator(
            modifier = Modifier
                .weight(0.8f)
                .height(20.dp)
                .clip(RoundedCornerShape(12.dp)),
            color = color,
            progress = { indicatorValue.toFloat() / MAX_STAT },
            stat = indicatorValue,
        )
        Spacer(Modifier.weight(0.1f))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CreatureSpeedAndArmorBlock(
    speedStats: Map<MovingType, String>,
    creatureArmor: List<ArmorPresentationModel>
) {
    val pagerState = rememberPagerState { creatureArmor.size }
    Row {
        Column(
            modifier = Modifier
                .weight(0.5f)
                .padding(start = 16.dp),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = DesignStrings.creature_speed_header_text,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            )
            Spacer(Modifier.height(16.dp))
            speedStats.forEach { entry ->
                Row {
                    Text(
                        text = entry.key.getTitleByMovingType(entry.value),
                        fontSize = 18.sp,
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .padding(end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = DesignStrings.creature_armor_header_text,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            )
            Spacer(Modifier.height(16.dp))
            HorizontalPager(
                modifier = Modifier.wrapContentSize(),
                state = pagerState,
            ) { page ->
                ArmorCard(
                    armorName = creatureArmor[page].type,
                    armorClass = creatureArmor[page].value,
                )
            }
        }
    }
}

@Composable
private fun ArmorCard(
    armorName: String,
    armorClass: Int,
) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 64.dp),
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = armorName,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = armorClass.toString(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun CreatureExperienceGain(
    xpGain: Int,
) {
    val commonModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    HeadedBlock(
        modifier = commonModifier,
        headerText = DesignStrings.creature_exp_header_text
    ) {
        VeldStatIndicator(
            modifier = commonModifier
                .height(20.dp)
                .clip(RoundedCornerShape(12.dp)),
            progress = { xpGain.toFloat() / MAX_XP },
            color = colors.charisma,
            stat = xpGain,
        )
    }
}

@Composable
private fun CreatureChallengeRating(
    challengeRating: Float,
) {
    val commonModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    HeadedBlock(
        modifier = commonModifier,
        headerText = DesignStrings.creature_challenge_header_text
    ) {
        VeldStatIndicator(
            modifier = commonModifier
                .height(20.dp)
                .clip(RoundedCornerShape(12.dp)),
            progress = { challengeRating / MAX_CHALLENGE_RATING },
            color = VeldTheme.colors.strength,
            stat = challengeRating.toInt(),
        )
    }
}