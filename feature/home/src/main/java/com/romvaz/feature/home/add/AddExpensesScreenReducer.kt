package com.romvaz.feature.home.add

import com.core.domain.model.room.ExpensesRoomModel
import com.core.store.Reducer


class AddExpensesScreenReducer : Reducer<AddExpensesUiState, AddExpensesScreenAction> {

    override fun reduce(
        state: AddExpensesUiState,
        action: AddExpensesScreenAction
    ): AddExpensesUiState =
        when (action) {
            is AddExpensesScreenAction.OnSaveExpense -> state.onSaveExpense()
            is AddExpensesScreenAction.OnAmountUpdate -> state.onAmountUpdate(action)
            is AddExpensesScreenAction.OnChipSelected -> state.onChipSelected(action)
            is AddExpensesScreenAction.OnSaved -> state.onExpenseSaved()
            is AddExpensesScreenAction.OnExpenseFetch -> state.onExpenseFetch(action)
            else -> state
        }
}

sealed interface AddExpensesScreenAction {
    data object OnSaveExpense : AddExpensesScreenAction

    data class OnAmountUpdate(
        val amount: String
    ) : AddExpensesScreenAction

    data class OnChipSelected(
        val type: String
    ) : AddExpensesScreenAction

    data object OnSaved : AddExpensesScreenAction

    data object OnGetExpense : AddExpensesScreenAction

    data class OnExpenseFetch(
        val expensesRoomModel: ExpensesRoomModel
    ) : AddExpensesScreenAction

    data object OnNothing : AddExpensesScreenAction
}
