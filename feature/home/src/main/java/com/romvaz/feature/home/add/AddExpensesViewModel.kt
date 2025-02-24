package com.romvaz.feature.home.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.store.Store
import com.core.ui.navigation.Navigator
import com.romvaz.feature.home.add.middlewares.AddExpenseMiddleware
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddExpensesViewModel @Inject constructor(
    private val navigator: Navigator,
    addExpenseMiddleware: AddExpenseMiddleware
) : ViewModel() {

    private val store = Store(
        AddExpensesUiState(),
        AddExpensesScreenReducer(),
        viewModelScope,
        listOf(
            addExpenseMiddleware
        )
    )

    fun observe(): StateFlow<AddExpensesUiState> = store.observe()

    fun updateAmount(amount: String) =
        store.dispatch(AddExpensesScreenAction.OnAmountUpdate(amount))

    fun updateChipsState(type: String) =
        store.dispatch(AddExpensesScreenAction.OnChipSelected(type))
}