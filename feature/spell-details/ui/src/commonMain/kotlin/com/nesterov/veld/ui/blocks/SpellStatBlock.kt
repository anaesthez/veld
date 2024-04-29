package com.nesterov.veld.ui.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.presentation.model.utils.StatType
import com.nesterov.veld.ui.TRANSPARENCY_ALPHA
import com.nesterov.veld.ui.getByStatType

@Composable
internal fun SpellStatBlock(
    statTypes: Map<StatType, String>,
    schoolColor: Color,
) {
    statTypes.forEach { type ->
        SpellStatItem(
            schoolColor = schoolColor,
            statType = type.key,
            statValue = type.value,
        )
    }
}

@Composable
internal fun SpellStatItem(
    schoolColor: Color,
    statType: StatType,
    statValue: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = schoolColor.copy(alpha = TRANSPARENCY_ALPHA),
                shape = RoundedCornerShape(16.dp),
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = statType.getByStatType(),
            fontWeight = FontWeight.SemiBold,
            color = colors.textColorSecondary,
            fontSize = 16.sp,
        )
        Text(
            modifier = Modifier.padding(12.dp),
            text = statValue,
            fontWeight = FontWeight.SemiBold,
            color = colors.textColorSecondary,
            fontSize = 16.sp,
        )
    }
}