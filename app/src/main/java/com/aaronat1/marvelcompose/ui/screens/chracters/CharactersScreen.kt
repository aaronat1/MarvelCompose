package com.aaronat1.marvelcompose.ui.screens.chracters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.aaronat1.marvelcompose.R

import com.aaronat1.marvelcompose.data.entities.Character
import com.aaronat1.marvelcompose.data.repositories.CharactersRepository
import com.aaronat1.marvelcompose.ui.screens.chracterdetail.AppBarOverflowMenu

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {
    var charactersState by rememberSaveable { mutableStateOf(emptyList<Character>()) }
    
    //Se ejecuta solo una vez
    LaunchedEffect(true) {
        charactersState = CharactersRepository.getCharacters()
    }
    
    CharactersScreen(characters = charactersState, onClick = onClick)
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun CharactersScreen(characters: List<Character>, onClick: (Character) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name))}
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            cells = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(characters) {
                CharacterItem(
                    character = it,
                    modifier = Modifier.clickable {
                        onClick(it)
                    }
                )
            }
        }
    }

}

@ExperimentalMaterialApi
@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
            Card {
                Image(
                    painter = rememberImagePainter(character.thumbnail),
                    contentDescription = character.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ){
                    AppBarOverflowMenu(urls = character.urls)
                }

            }

        Text(
            text = character.name,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp))
    }
}
