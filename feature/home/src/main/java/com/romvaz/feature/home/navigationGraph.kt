package com.romvaz.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.core.domain.routes.HomeRoute
import com.romvaz.feature.home.add.AddExpenseScreen
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

        composable(
            route = HomeRoute.AddExpenseRoute.route,
            arguments = listOf(
                navArgument(HomeRoute.AddExpenseRoute.ARG_ID) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            AddExpenseScreen()
        }
    }
}
