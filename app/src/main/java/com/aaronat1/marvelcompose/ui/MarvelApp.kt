package com.aaronat1.marvelcompose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.aaronat1.marvelcompose.R
import com.aaronat1.marvelcompose.ui.navigation.*
import com.aaronat1.marvelcompose.ui.theme.MarvelComposeTheme
import com.aaronat1.marvelcompose.ui.theme.RedDark
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MarvelApp() {

    val appState =  rememberMarvelAppState()

    MarvelScreen {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    navigationIcon = {
                        if (appState.showUpNavigation) {
                            AppBarIcon(
                                imageVector = Icons.Default.ArrowBack,
                                onClick = { appState.onUpClick() })
                        } else {
                            AppBarIcon(
                                imageVector = Icons.Default.Menu,
                                onClick = { appState.onMenuClick()}
                            )
                        }

                    },

                )
            },
            bottomBar = {
                if (appState.showBottomNavigation) {
                    AppBottomNavigation(
                        bottonNavOptions = MarvelAppState.BOTTOM_NAV_OPTIONS,
                        currentRoute = appState.currentRoute,
                        onNavItemClick = { navItem ->
                            appState.onNavItemClick(navItem)
                        } )
                }
            },
            drawerContent = {
                DrawerContent(
                    drawerOptions = MarvelAppState.DRAWER_OPTIONS,
                    selectedIndex = appState.drawerSelectedIndex,
                    onOptionClick = { navItem ->
                        appState.onDrawerOptionClick(navItem)
                    }
                ) },
            scaffoldState = appState.scaffoldState
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(appState.navController)
            }
        }
        SetStatusBarColorEffect()
    }

}

@Composable
fun SetStatusBarColorEffect(
    color: Color = MaterialTheme.colors.primaryVariant,
    systemUiController: SystemUiController = rememberSystemUiController()
) {
    SideEffect {
        systemUiController.setStatusBarColor(
            color = color
        )
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