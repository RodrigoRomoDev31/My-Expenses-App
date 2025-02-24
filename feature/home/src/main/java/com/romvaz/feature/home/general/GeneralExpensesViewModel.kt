package com.romvaz.feature.home.general

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.model.room.ExpensesRoomModel
import com.core.domain.routes.HomeRoute
import com.core.store.Store
import com.core.ui.navigation.NavigationCommand
import com.core.ui.navigation.Navigator
import com.romvaz.feature.home.general.middlewares.DeleteExpenseMiddleware
import com.romvaz.feature.home.general.middlewares.GetUserExpensesMiddleware
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GeneralExpensesViewModel @Inject constructor(
    private val navigator: Navigator,
    getUserExpensesMiddleware: GetUserExpensesMiddleware,
    deleteExpenseMiddleware: DeleteExpenseMiddleware
): ViewModel(){

    private val store = Store(
        GeneralExpensesUiState(),
        GeneralExpensesScreenReducer(),
        viewModelScope,
        listOf(
            getUserExpensesMiddleware,
            deleteExpenseMiddleware
        )
    )

    fun observe(): StateFlow<GeneralExpensesUiState> = store.observe()

    fun navigateToAdd() =
        navigator.navigate(NavigationCommand.NavigateTo(HomeRoute.AddExpenseRoute.createRoute(0)))

    fun changeCurrentMonth(forward: Boolean) =
        store.dispatch(GeneralExpensesScreenAction.OnChangeMonth(forward))

    fun deleteExpense(expensesRoomModel: ExpensesRoomModel) =
        store.dispatch(GeneralExpensesScreenAction.OnDeleteExpense(expensesRoomModel))

    fun navigateToEditExpense(id: Int) =
        navigator.navigate(NavigationCommand.NavigateTo(HomeRoute.AddExpenseRoute.createRoute(id)))
}