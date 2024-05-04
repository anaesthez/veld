package ru.nesterov.veld.common_ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesterov.veld.design_system.theme.VeldTheme

@Composable
fun VieldSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    placeholderText: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    TextField(
        modifier = modifier,
        value = query,
        singleLine = true,
        onValueChange = onQueryChange,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(16.dp),
        placeholder = { Text(text = placeholderText) },
        textStyle = TextStyle(fontSize = 16.sp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = VeldTheme.colors.materialColors.background,
            unfocusedContainerColor = VeldTheme.colors.materialColors.background,
            disabledContainerColor = VeldTheme.colors.materialColors.background,
            unfocusedBorderColor = Color.Transparent, // Bottom line handling
            disabledBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
        ),
    )
}