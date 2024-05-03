package com.nesterov.veld.design_system.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList

@Composable
fun <T> VeldItemsLazyRow(
    modifier: Modifier = Modifier,
    itemsList: ImmutableList<T>,
    lazyRowListState: LazyListState = rememberLazyListState(),
    items: @Composable LazyItemScope.(T) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        state = lazyRowListState,
    ){
        items(itemsList) {
            items(it)
        }
    }
}