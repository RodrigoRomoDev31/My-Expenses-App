package com.romvaz.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.domain.routes.HomeRoute
import com.romvaz.feature.home.general.GeneralExpensesScreen
import com.romvaz.feature.home.splash.SplashScreen

// Navigation Graph for Main Module
fun NavGraphBuilder.homeGraph() {
    navigation(
        startDestination = HomeRoute.SplashRoute.route,
        route = HomeRoute.RootRoute.route
    ) {
        composable(
            HomeRoute.SplashRoute.route
        ) {
            SplashScreen()
        }

        composable(
            HomeRoute.GeneralRoute.route
        ){
            GeneralExpensesScreen()
        }
    }
}
