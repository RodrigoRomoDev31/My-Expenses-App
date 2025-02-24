package com.romvaz.feature.home.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.routes.HomeRoute
import com.core.store.Store
import com.core.ui.navigation.NavigationCommand
import com.core.ui.navigation.Navigator
import com.romvaz.feature.home.add.middlewares.AddExpenseMiddleware
import com.romvaz.feature.home.add.middlewares.GetExpenseMiddleware
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddExpensesViewModel @Inject constructor(
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle,
    addExpenseMiddleware: AddExpenseMiddleware,
    getExpenseMiddleware: GetExpenseMiddleware
) : ViewModel() {

    private val id: Int =
        checkNotNull(savedStateHandle[HomeRoute.AddExpenseRoute.ARG_ID])

    private val store = Store(
        AddExpensesUiState(id = id),
        AddExpensesScreenReducer(),
        viewModelScope,
        listOf(
            addExpenseMiddleware,
            getExpenseMiddleware
        )
    )

    fun observe(): StateFlow<AddExpensesUiState> = store.observe()

    init {
        if (id != 0)
            store.dispatch(AddExpensesScreenAction.OnGetExpense)
    }

    fun updateAmount(amount: String) =
        store.dispatch(AddExpensesScreenAction.OnAmountUpdate(amount))

    fun updateChipsState(type: String) =
        store.dispatch(AddExpensesScreenAction.OnChipSelected(type))

    fun saveExpense() =
        store.dispatch(AddExpensesScreenAction.OnSaveExpense)

    fun navigateBack() =
        navigator.navigate(NavigationCommand.PopBackstack)
}