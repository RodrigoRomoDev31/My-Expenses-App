package com.romvaz.feature.home.add

import com.core.ui.utils.ExpensesTypes

fun AddExpensesUiState.onAmountUpdate(
    action: AddExpensesScreenAction.OnAmountUpdate
): AddExpensesUiState =
    copy(
        amount = action.amount,
        disclaimerShowed = action.amount.isEmpty()
    )

fun AddExpensesUiState.onSaveExpense(): AddExpensesUiState {
    val type = when {
        entertainmentChip -> ExpensesTypes.Entertainment.type
        foodChip -> ExpensesTypes.Food.type
        streamingChip -> ExpensesTypes.StreamingServices.type
        stationaryChip -> ExpensesTypes.Stationary.type
        homeLoanChip -> ExpensesTypes.HomeLoan.type
        personalLoanChip -> ExpensesTypes.PersonalLoan.type
        else -> ""
    }
    return this.copy(
        type = type
    )
}

fun AddExpensesUiState.onChipSelected(
    action: AddExpensesScreenAction.OnChipSelected
): AddExpensesUiState {

    var newState = updateChipFlags(this)

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

fun AddExpensesUiState.onExpenseSaved(): AddExpensesUiState {
    val newState = updateChipFlags(this, true)
    return newState.copy(
        amount = "",
        disclaimerShowed = true,
        counter = this.counter + 1
    )
}

fun AddExpensesUiState.onExpenseFetch(
    action: AddExpensesScreenAction.OnExpenseFetch
): AddExpensesUiState {
    var newState = updateChipFlags(this)
    newState = when (action.expensesRoomModel.expenseType) {
        ExpensesTypes.Entertainment.type -> newState.copy(entertainmentChip = true)
        ExpensesTypes.Food.type -> newState.copy(foodChip = true)
        ExpensesTypes.StreamingServices.type -> newState.copy(streamingChip = true)
        ExpensesTypes.Stationary.type -> newState.copy(stationaryChip = true)
        ExpensesTypes.HomeLoan.type -> newState.copy(homeLoanChip = true)
        ExpensesTypes.PersonalLoan.type -> newState.copy(personalLoanChip = true)
        else -> newState
    }

    newState = newState.copy(
        amount = action.expensesRoomModel.expense,
        time = action.expensesRoomModel.date,
        type = action.expensesRoomModel.expenseType,
        disclaimerShowed = false
    )

    return newState
}

private fun updateChipFlags(
    state: AddExpensesUiState,
    savedFlow: Boolean = false
): AddExpensesUiState =
    state.copy(
        entertainmentChip = savedFlow,
        foodChip = false,
        streamingChip = false,
        stationaryChip = false,
        homeLoanChip = false,
        personalLoanChip = false
    )