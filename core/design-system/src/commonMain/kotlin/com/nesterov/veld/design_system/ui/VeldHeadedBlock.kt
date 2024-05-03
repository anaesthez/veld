package com.nesterov.veld.design_system.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeadedBlock(
    modifier: Modifier = Modifier,
    headerText: String,
    headedContent: @Composable ColumnScope.() -> Unit,
) {
    Column {
        Text(
            modifier = modifier,
            text = headerText,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(Modifier.size(8.dp))
        headedContent()
    }
}