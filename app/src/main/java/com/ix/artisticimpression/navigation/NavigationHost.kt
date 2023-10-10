package com.ix.artisticimpression.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ix.artisticimpression.ui.screens.details.DetailsScreen
import com.ix.artisticimpression.ui.screens.explore.ExploreScreen
import com.ix.artisticimpression.ui.screens.favorites.FavoritesScreen
import com.ix.artisticimpression.ui.screens.quiz.QuizScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
) {
    NavHost(
        navController,
        startDestination = Routes.Main.route,
        route = Routes.Root.route,
    ) {
        fun onNavigateToDetails() {
            navController.navigate(
                route = Routes.Details.route,
            ) {
                popUpTo(Routes.Main.route)
            }
        }

        composable(Routes.Main.route) { entry ->
            BottomTabNavHost(onNavigateToDetails = { onNavigateToDetails() })
        }

        composable(Routes.Details.route) { entry ->
            DetailsScreen(
                onNavigateBack = { navController.popBackStack() },
            )
        }
    }
}

@Composable
fun BottomTabNavHost(onNavigateToDetails: () -> Unit) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavBar(navController)
        },
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Routes.Quiz.route,
            Modifier.padding(innerPadding),
        ) {
            composable(Routes.Quiz.route) {
                QuizScreen(onNavigateToDetails = onNavigateToDetails)
            }
            composable(Routes.Favorites.route) {
                FavoritesScreen(onNavigateToDetails = onNavigateToDetails)
            }
            composable(Routes.Explore.route) {
                ExploreScreen(onNavigateToDetails = onNavigateToDetails)
            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}
