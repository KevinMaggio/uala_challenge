package com.google.uala_challenge.features.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.uala_challenge.features.details.presenter.screen.DetailScreen
import com.google.uala_challenge.features.home.presenter.screen.CitiesScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {
        composable(Routes.HOME) {
            CitiesScreen(
                modifier = Modifier.fillMaxSize(),
                onNavigateToDetail = { cityId ->
                    navController.navigate(Routes.detail(cityId))
                }
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument(Routes.ARG_CITY_ID) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val cityId = backStackEntry.arguments?.getInt(Routes.ARG_CITY_ID) ?: 0
            DetailScreen(
                cityId = cityId,
                onBack = { navController.popBackStack() },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
