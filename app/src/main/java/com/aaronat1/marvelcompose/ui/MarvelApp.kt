package com.aaronat1.marvelcompose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.aaronat1.marvelcompose.R
import com.aaronat1.marvelcompose.ui.navigation.AppBottomNavigation
import com.aaronat1.marvelcompose.ui.navigation.NavItem
import com.aaronat1.marvelcompose.ui.navigation.Navigation
import com.aaronat1.marvelcompose.ui.navigation.navigatePopingUpToStartDestination
import com.aaronat1.marvelcompose.ui.theme.MarvelComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MarvelApp() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    val showUpNavigation = currentRoute !in NavItem.values().map { it.navCommand.route}

    MarvelScreen {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    navigationIcon = if (showUpNavigation) {
                        {
                            com.aaronat1.marvelcompose.ui.navigation.AppBarIcon(
                                imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
                                onClick = { navController.popBackStack() })
                        }
                    } else null
                )
            },
            bottomBar = {
                AppBottomNavigation(currentRoute = currentRoute, onNavItemClick = { navItem ->
                    navController.navigatePopingUpToStartDestination(navItem.navCommand.route)
                } )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController)
            }
        }
    }
}



@Composable
fun MarvelScreen(content: @Composable () -> Unit) {
    MarvelComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}