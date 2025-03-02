package com.core.domain.routes

sealed interface HomeRoute : Route {

    // Root route for the Home module
    data object RootRoute : HomeRoute {
        override val route = "home"
    }

    // Splash route for the Home module
    data object SplashRoute : HomeRoute {
        override val route = "home/splash"
    }

    // Main route for the Home module
    data object GeneralRoute : HomeRoute {
        override val route = "home/general"
    }

    data object AddExpenseRoute : HomeRoute {
        const val ARG_ID = "expense_id"
        override val route = "home/add/{$ARG_ID}"

        fun createRoute(expenseId: Int) = "home/add/$expenseId"
    }
}
