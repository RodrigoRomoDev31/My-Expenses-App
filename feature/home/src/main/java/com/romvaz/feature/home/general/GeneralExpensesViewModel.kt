package com.romvaz.feature.home.general

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.store.Store
import com.core.ui.navigation.Navigator
import com.romvaz.feature.home.general.middlewares.GetUserExpensesMiddleware
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GeneralExpensesViewModel @Inject constructor(
    private val navigator: Navigator,
    getUserExpensesMiddleware: GetUserExpensesMiddleware
): ViewModel(){

    private val store = Store(
        GeneralExpensesUiState(),
        GeneralExpensesScreenReducer(),
        viewModelScope,
        listOf(
            getUserExpensesMiddleware
        )
    )

    fun observe(): StateFlow<GeneralExpensesUiState> = store.observe()
}