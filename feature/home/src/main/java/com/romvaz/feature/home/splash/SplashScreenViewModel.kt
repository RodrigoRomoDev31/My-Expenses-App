package com.romvaz.feature.home.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.core.domain.routes.HomeRoute
import com.core.ui.navigation.NavigationCommand
import com.core.ui.navigation.Navigator
import com.core.ui.utils.DELAY_TIME_2000
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @param navigator The [Navigator] responsible for managing navigation actions within the app.
 * @param userPreferenceService The service used to manage and persist user preferences.
 */
@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    init {
        viewModelScope.launch {
            delay(DELAY_TIME_2000)
            navigator.navigate(
                NavigationCommand.NavigateTo(
                    HomeRoute.GeneralRoute.route,
                    NavOptions.Builder().setPopUpTo(HomeRoute.SplashRoute.route, true)
                        .build()
                )
            )
        }
    }
}

