package com.nesterov.veld.design_system.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList

@Composable
fun <T> VeldClassesLazyRow(
    modifier: Modifier = Modifier,
    charClasses: ImmutableList<T>,
    lazyRowListState: LazyListState,
    items: @Composable LazyItemScope.(T) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        state = lazyRowListState,
    ){
        items(charClasses) {
            items(it)
        }
    }
}