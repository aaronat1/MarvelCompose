package com.aaronat1.marvelcompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.aaronat1.marvelcompose.ui.screens.chracterdetail.CharacterDetailScreen
import com.aaronat1.marvelcompose.ui.screens.chracters.CharactersScreen

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavItem.Characters.route
    ) {
        charactersNav(navController)
    }
}


@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
private fun NavGraphBuilder.charactersNav(
    navController: NavController
) {
    composable(NavItem.Characters) {
        CharactersScreen(
            onClick = { character ->
                navController.navigate(NavItem.CharacterDetail.createRoute(character.id))
            }
        )
    }

    composable(NavItem.CharacterDetail) {
        val id = it.findArg<Int>(NavArg.ItemId)
        CharacterDetailScreen(id)
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}

