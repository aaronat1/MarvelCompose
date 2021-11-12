package com.aaronat1.marvelcompose.ui.screens.chracterdetail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import com.aaronat1.marvelcompose.data.entities.Url

@ExperimentalMaterialApi
@Composable
fun AppBarOverflowMenu(urls: List<Url>, modifier: Modifier = Modifier) {

    if (urls.isEmpty()) return

    var showMenu by remember { mutableStateOf(false) }
    var uriHandler = LocalUriHandler.current

    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More Actions",
            modifier = modifier
        )
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            urls.forEach {
                DropdownMenuItem(onClick = {
                    uriHandler.openUri(it.url)
                    showMenu = false
                }) {
                    ListItem(text = { Text(it.type) })
                }
            }
        }
    }
}