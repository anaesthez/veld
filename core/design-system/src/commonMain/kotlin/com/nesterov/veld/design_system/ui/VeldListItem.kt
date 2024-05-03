package com.nesterov.veld.design_system.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesterov.veld.design_system.theme.VeldTheme.colors

@Composable
fun VeldListItem(
    modifier: Modifier = Modifier,
    backgroundColor: Color = colors.materialColors.surface,
    title: String,
    leadingContent: @Composable (() -> Unit)? = null,
    onItemClick: () -> Unit,
) {
    ListItem(
        modifier = modifier
            .semantics(mergeDescendants = true) { }
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = true, onClick = onItemClick),
        leadingContent = leadingContent,
        headlineContent = {
            Text(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                maxLines = 1,
                color = colors.textColorPrimary,
                text = title,
            )
        },
        colors = ListItemDefaults.colors(containerColor = backgroundColor)
    )
}