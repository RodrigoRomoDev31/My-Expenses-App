package com.romvaz.feature.home.add

data class AddExpensesUiState(
    val entertainmentChip: Boolean = true,
    val foodChip: Boolean = false,
    val streamingChip: Boolean = false,
    val stationaryChip: Boolean = false,
    val homeLoanChip: Boolean = false,
    val personalLoanChip: Boolean = false,
    val disclaimerShowed: Boolean = true,
    val amount: String = "",
    val counter: Int = 0,
    val type: String = ""
)
