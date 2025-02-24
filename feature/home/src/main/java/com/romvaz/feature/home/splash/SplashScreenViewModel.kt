package com.romvaz.feature.home.splash

import androidx.lifecycle.ViewModel
import com.core.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @param navigator The [Navigator] responsible for managing navigation actions within the app.
 * @param userPreferenceService The service used to manage and persist user preferences.
 */
@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
}
