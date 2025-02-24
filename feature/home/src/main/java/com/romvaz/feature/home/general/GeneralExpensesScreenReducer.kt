package com.romvaz.feature.home.general

import com.core.domain.model.room.ExpensesRoomModel
import com.core.store.Reducer


class GeneralExpensesScreenReducer : Reducer<GeneralExpensesUiState, GeneralExpensesScreenAction> {

    override fun reduce(
        state: GeneralExpensesUiState,
        action: GeneralExpensesScreenAction
    ): GeneralExpensesUiState =
        when (action) {
            is GeneralExpensesScreenAction.OnGetUserExpenses -> state.copy(expensesList = action.expenses)
        }
}

sealed interface GeneralExpensesScreenAction {

    data class OnGetUserExpenses(
        val expenses: List<ExpensesRoomModel>
    ) : GeneralExpensesScreenAction
}
