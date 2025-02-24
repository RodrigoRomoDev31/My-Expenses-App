package com.romvaz.feature.home.general

import com.core.domain.model.room.ExpensesRoomModel
import com.core.store.Reducer


class GeneralExpensesScreenReducer : Reducer<GeneralExpensesUiState, GeneralExpensesScreenAction> {

    override fun reduce(
        state: GeneralExpensesUiState,
        action: GeneralExpensesScreenAction
    ): GeneralExpensesUiState =
        when (action) {
            is GeneralExpensesScreenAction.OnGetUserExpenses -> state.onGetUserExpenses(action)
            is GeneralExpensesScreenAction.OnChangeMonth -> state.onChangeMonth(action)
            is GeneralExpensesScreenAction.OnDeleteExpense -> state.copy(expensesRoomModel = action.expense)
            is GeneralExpensesScreenAction.OnExpenseDeleted -> state.copy(counter = state.counter + 1)
        }
}


sealed interface GeneralExpensesScreenAction {

    data class OnGetUserExpenses(
        val expenses: List<ExpensesRoomModel>
    ) : GeneralExpensesScreenAction

    data class OnChangeMonth(
        val forward: Boolean
    ) : GeneralExpensesScreenAction

    data class OnDeleteExpense(
        val expense: ExpensesRoomModel
    ) : GeneralExpensesScreenAction

    data object OnExpenseDeleted : GeneralExpensesScreenAction
}
