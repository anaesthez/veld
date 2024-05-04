package com.nesterov.veld.design_system.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nesterov.veld.сore.design_system.strings.DesignStrings

@Composable
fun VeldFailureScreen(
    errorText: String,
    onBackClick: (() -> Unit)? = null,
    onRetryClick: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (onBackClick != null) {
            VeldAppBar(
                onBackClick = onBackClick,
                titleText = DesignStrings.classes_details_spell_failure_app_bar
            )
        }
        Column {
            Text(
                text = errorText,
            )
            Spacer(modifier = Modifier.size(8.dp))
            if (onRetryClick != null) {
                Button(
                    onClick = onRetryClick,
                    content = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = DesignStrings.classes_details_spell_failure_retry
                        )
                    }
                )
            }
        }
    }
}