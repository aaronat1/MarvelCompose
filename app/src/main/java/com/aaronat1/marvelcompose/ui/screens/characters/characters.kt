package com.aaronat1.marvelcompose.ui.screens.characters

import MarvelItemDetailScreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import coil.annotation.ExperimentalCoilApi
import com.aaronat1.marvelcompose.data.entities.Character
import com.aaronat1.marvelcompose.ui.screens.commons.MarvelItemsListScreen
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun CharactersScreen(onClick: (Character) -> Unit, viewModel: CharactersViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    MarvelItemsListScreen(
        loading = state.loading,
        items = state.items,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    MarvelItemDetailScreen(
        loading = state.loading,
        marvelItem = state.character
    )
}