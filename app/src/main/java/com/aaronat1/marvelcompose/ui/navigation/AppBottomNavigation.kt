package com.aaronat1.marvelcompose.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun AppBottomNavigation(
    currentRoute: String,
    onNavItemClick: (NavItem) -> Unit,
    bottonNavOptions: List<NavItem>
) {
    BottomNavigation {
        bottonNavOptions.forEach { item ->
            val title = stringResource(item.title)
            BottomNavigationItem(
                selected = currentRoute.contains(item.navCommand.feature.route),
                onClick = { onNavItemClick(item) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name
                    )
                },
                label = { Text(text = title) }
            )
        }
    }
}