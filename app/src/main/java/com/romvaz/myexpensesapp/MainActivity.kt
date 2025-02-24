package com.romvaz.myexpensesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.core.ui.navigation.Navigator
import com.core.ui.navigation.NavigatorHandler
import com.core.ui.theme.MyExpensesAppTheme
import com.romvaz.core.domain.routes.HomeRoute
import com.romvaz.feature.home.homeGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val navController = rememberNavController()
            MyExpensesAppTheme {
                Column {
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute.RootRoute.route,
                        modifier = Modifier.weight(1.0f)
                    ) {
                        homeGraph()
                    }
                }

                NavigatorHandler(navigator = navigator, navController = navController)
            }
        }
    }
}