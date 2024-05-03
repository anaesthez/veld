package com.nesterov.veld.design_system.ui

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nesterov.veld.design_system.theme.VeldIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeldAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    titleText: String,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
            ) {
                Icon(
                    imageVector = VeldIcons.backArrow,
                    contentDescription = null,
                )
            }
        }, title = {
            Text(
                text = titleText,
            )
        }
    )
}