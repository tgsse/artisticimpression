package com.ix.artisticimpression.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ix.artisticimpression.R
import com.ix.artisticimpression.navigation.util.NavigationTabWithIcon

private val mainTabs = listOf(
    NavigationTabWithIcon(
        route = Routes.Quiz.route,
        label = R.string.label_screen_quiz,
        icon = Icons.Outlined.QuestionMark,
    ),
    NavigationTabWithIcon(
        route = Routes.Favorites.route,
        label = R.string.label_screen_favorites,
        icon = Icons.Outlined.Favorite,
    ),
    NavigationTabWithIcon(
        route = Routes.Explore.route,
        label = R.string.label_screen_explore,
        icon = Icons.Outlined.Explore,
    ),
)

@Composable
fun NavBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        mainTabs.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        screen.icon,
                        contentDescription = null,
                    )
                },
                label = { Text(stringResource(screen.label)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
            )
        }
    }
}
