package com.romvaz.feature.home.add

import com.core.store.Reducer
import com.core.ui.utils.ExpensesTypes


class AddExpensesScreenReducer : Reducer<AddExpensesUiState, AddExpensesScreenAction> {

    override fun reduce(
        state: AddExpensesUiState,
        action: AddExpensesScreenAction
    ): AddExpensesUiState =
        when (action) {
            is AddExpensesScreenAction.OnSaveExpense -> state.onSaveExpense(action)
            is AddExpensesScreenAction.OnAmountUpdate -> state.copy(amount = action.amount)
            is AddExpensesScreenAction.OnChipSelected -> state.onChipSelected(action)
            else -> state
        }

    private fun AddExpensesUiState.onSaveExpense(
        action: AddExpensesScreenAction.OnSaveExpense
    ): AddExpensesUiState {
        val type = ExpensesTypes.withType(action.type).toString()
        return this.copy(
            amount = action.amount,
            type = type
        )
    }

    private fun AddExpensesUiState.onChipSelected(
        action: AddExpensesScreenAction.OnChipSelected
    ): AddExpensesUiState {
        var newState = this.copy(
            entertainmentChip = false,
            foodChip = false,
            streamingChip = false,
            stationaryChip = false,
            homeLoanChip = false,
            personalLoanChip = false
        )

        newState = when (action.type) {
            ExpensesTypes.Entertainment.type -> newState.copy(entertainmentChip = true)
            ExpensesTypes.Food.type -> newState.copy(foodChip = true)
            ExpensesTypes.StreamingServices.type -> newState.copy(streamingChip = true)
            ExpensesTypes.Stationary.type -> newState.copy(stationaryChip = true)
            ExpensesTypes.HomeLoan.type -> newState.copy(homeLoanChip = true)
            ExpensesTypes.PersonalLoan.type -> newState.copy(personalLoanChip = true)
            else -> newState
        }

        return newState
    }
}

sealed interface AddExpensesScreenAction {
    data class OnSaveExpense(
        val type: String,
        val amount: String
    ) : AddExpensesScreenAction

    data class OnAmountUpdate(
        val amount: String
    ) : AddExpensesScreenAction

    data class OnChipSelected(
        val type: String
    ) : AddExpensesScreenAction

    data object OnNothing : AddExpensesScreenAction
}
