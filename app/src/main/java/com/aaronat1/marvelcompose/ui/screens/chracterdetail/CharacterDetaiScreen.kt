package com.aaronat1.marvelcompose.ui.screens.chracterdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.aaronat1.marvelcompose.MarvelApp
import com.aaronat1.marvelcompose.data.entities.Character
import com.aaronat1.marvelcompose.data.entities.Reference
import com.aaronat1.marvelcompose.data.repositories.CharactersRepository
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import coil.annotation.ExperimentalCoilApi
import com.aaronat1.marvelcompose.R
import com.aaronat1.marvelcompose.ui.navigation.ArrowBackIcon

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CharacterDetailScreen(characterId: Int, onUpClick: () -> Unit) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        characterState =  CharactersRepository.findCharacter(characterId)
    }
    characterState?.let {
            CharacterDetailScreen(it, onUpClick)
    }

}

@ExperimentalMaterialApi
@Composable
fun CharacterDetailScreen(character: Character, onUpClick: () -> Unit) {

    CharacterDetailScaffold(
        character = character,
        onUpClick = onUpClick
    )
    { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            item {
                Header(character)
            }
            section(Icons.Default.Collections, R.string.Series, character.series)
            section(Icons.Default.Event, R.string.Events, character.events)
            section(Icons.Default.Book, R.string.Comics, character.comics)
            section(Icons.Default.Bookmark, R.string.Stories, character.stories)
        }

    }

}

@ExperimentalMaterialApi
fun LazyListScope.section(icon: ImageVector, name: Int, items: List<Reference>) {

    if ( items.isEmpty()) return

    item {
        Text(
            text = stringResource(id = name),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )
    }
    items(items) {
        ListItem(
            icon = { Icon(imageVector = icon, contentDescription = null) },
            text = { Text(it.name) }
        )
    }
}

@Composable
fun Header(character: Character) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(character.thumbnail),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

/*
@ExperimentalMaterialApi
@Preview(widthDp = 400, heightDp = 700)
@Composable
fun CharacterDetailScreenPreview() {
    val character = Character(
        1,
        "Iron Man",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
        "",
        listOf(Reference("Comic1"), Reference("Comic2")),
        listOf(Reference("Comic1"), Reference("Comic2")),
        listOf(Reference("Comic1"), Reference("Comic2")),
        listOf(Reference("Comic1"), Reference("Comic2")),
    )
    MarvelApp {
        CharacterDetailScreen(character = character, onUpClick = {})
    }
}*/
