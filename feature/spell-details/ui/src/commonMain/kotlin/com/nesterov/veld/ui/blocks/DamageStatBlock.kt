package com.nesterov.veld.ui.blocks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.presentation.model.utils.AreaType
import com.nesterov.veld.presentation.model.utils.SlotType
import com.nesterov.veld.ui.getAreaImageRes
import com.nesterov.veld.сore.design_system.strings.DesignStrings
import io.github.skeptick.libres.compose.painterResource
import kotlinx.collections.immutable.ImmutableMap

@Composable
internal fun DamageStatBlock(
    damageSlots: ImmutableMap<SlotType, String>,
    areaTypeTitle: String,
    rangeDistance: String,
    schoolColor: Color,
    damageType: String,
    areaType: AreaType,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        DamageHeaders(
            modifier = Modifier.fillMaxWidth(),
            areaTypeTitle = areaTypeTitle,
            damageType = damageType,
        )
        DamageStats(
            modifier = Modifier.fillMaxWidth(),
            hasRange = areaTypeTitle.isNotBlank(),
            rangeDistance = rangeDistance,
            damageSlots = damageSlots,
            schoolColor = schoolColor,
            areaType = areaType,
        )
    }
}

@Composable
internal fun DamageHeaders(
    modifier: Modifier = Modifier,
    areaTypeTitle: String,
    damageType: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
    ) {
        Row {
            Text(
                text = damageType,
                fontWeight = FontWeight.SemiBold,
                color = colors.textColorTertiary,
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier.align(Alignment.Bottom),
                text = DesignStrings.spell_details_damage_text,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Bottom)
                .padding(end = 24.dp),
            textAlign = TextAlign.Center,
            text = areaTypeTitle,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
internal fun DamageStats(
    modifier: Modifier = Modifier,
    damageSlots: ImmutableMap<SlotType, String>,
    hasRange: Boolean,
    schoolColor: Color,
    areaType: AreaType,
    rangeDistance: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Spacer(modifier = Modifier.size(8.dp))
            damageSlots.forEach { slot ->
                DamageSlot(
                    damagePerSlot = slot.value,
                    slotType = slot.key.getDamageByType(),
                    schoolColor = schoolColor,
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
        if (hasRange) {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(32.dp))
                Image(
                    modifier = Modifier.size(90.dp),
                    painter = painterResource(areaType.getAreaImageRes()),
                    contentDescription = null,
                )
                Text(
                    text = rangeDistance,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        }
    }
}

@Composable
private fun DamageSlot(
    schoolColor: Color,
    slotType: String,
    damagePerSlot: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(schoolColor, CircleShape)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 4.dp),
                textAlign = TextAlign.Center,
                color = colors.textColorPrimary,
                fontWeight = FontWeight.Bold,
                text = slotType,
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = damagePerSlot,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

private fun SlotType.getDamageByType(): String =
    when(this) {
        SlotType.SECOND -> "2"
        SlotType.THIRD -> "3"
        SlotType.FOURTH -> "4"
        SlotType.FIFTH -> "5"
        SlotType.SIXTH -> "6"
        SlotType.SEVENTH -> "7"
        SlotType.EIGHTH -> "8"
        SlotType.NINTH -> "9"
    }

