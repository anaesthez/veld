package com.nesterov.veld.design_system.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeadedBlock(
    modifier: Modifier = Modifier,
    paddings: PaddingValues = PaddingValues(0.dp),
    textAlign: TextAlign? = null,
    fontSize: TextUnit = 18.sp,
    color: Color = Color.Unspecified,
    headerText: String,
    headedContent: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier.padding(paddings)
    ) {
        Text(
            modifier = modifier,
            text = headerText,
            color = color,
            fontSize = fontSize,
            textAlign = textAlign,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(Modifier.size(8.dp))
        headedContent()
    }
}