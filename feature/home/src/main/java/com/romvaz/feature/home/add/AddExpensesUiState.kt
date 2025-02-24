package com.romvaz.feature.home.add

data class AddExpensesUiState(
    val entertainmentChip: Boolean = false,
    val foodChip: Boolean = false,
    val streamingChip: Boolean = false,
    val stationaryChip: Boolean = false,
    val homeLoanChip: Boolean = false,
    val personalLoanChip: Boolean = false,
    val amount: String = "",
    val type: String = ""
)
