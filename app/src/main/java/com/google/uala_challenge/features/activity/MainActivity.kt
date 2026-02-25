package com.google.uala_challenge.features.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.uala_challenge.features.details.presenter.screen.DetailScreen
import com.google.uala_challenge.features.home.presenter.screen.CitiesScreen
import com.google.uala_challenge.ui.theme.Uala_challengeTheme
import dagger.hilt.android.AndroidEntryPoint

private const val ROUTE_HOME = "home"
private const val ROUTE_DETAIL = "details/{cityId}"
private const val ARG_CITY_ID = "cityId"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Uala_challengeTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = ROUTE_HOME,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(ROUTE_HOME) {
                            CitiesScreen(
                                modifier = Modifier.fillMaxSize(),
                                onNavigateToDetail = { cityId ->
                                    navController.navigate("details/$cityId")
                                }
                            )
                        }
                        composable(
                            route = ROUTE_DETAIL,
                            arguments = listOf(navArgument(ARG_CITY_ID) { type = NavType.IntType })
                        ) { backStackEntry ->
                            val cityId = backStackEntry.arguments?.getInt(ARG_CITY_ID) ?: 0
                            DetailScreen(
                                cityId = cityId,
                                onBack = { navController.popBackStack() },
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}