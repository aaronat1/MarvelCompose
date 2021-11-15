package com.aaronat1.marvelcompose.ui.screens.commons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.aaronat1.marvelcompose.data.entities.MarvelItem

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun <T : MarvelItem> MarvelItemsListScreen(items: List<T>, onClick: (T) -> Unit) {
    MarvelItemsList(
        items = items,
        onItemClick = onClick,
        modifier = Modifier.padding()
    )
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun <T : MarvelItem> MarvelItemsList(
    items: List<T>,
    onItemClick: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp),
        modifier = modifier
    ) {
        items(items) {
            MarvelListItem(
                marvelItem = it,
                modifier = Modifier.clickable { onItemClick(it) }
            )
        }
    }
}