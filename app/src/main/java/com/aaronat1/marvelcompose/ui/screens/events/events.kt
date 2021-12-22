package com.aaronat1.marvelcompose.ui.screens.events

import MarvelItemDetailScreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import coil.annotation.ExperimentalCoilApi
import com.aaronat1.marvelcompose.data.entities.Event
import com.aaronat1.marvelcompose.ui.screens.commons.MarvelItemsListScreen
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun EventsScreen(onClick: (Event) -> Unit, viewModel: EventsViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    MarvelItemsListScreen(
        loading =  state.loading,
        items = state.items,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun EventDetailScreen(viewModel: EventDetailViewModel = viewModel()) {
    MarvelItemDetailScreen(
        loading = viewModel.state.loading,
        marvelItem = viewModel.state.event
    )
}