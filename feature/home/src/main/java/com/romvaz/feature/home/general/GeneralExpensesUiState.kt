package com.romvaz.feature.home.general

import com.core.domain.model.room.ExpensesRoomModel

data class GeneralExpensesUiState (
    val expensesList: List<ExpensesRoomModel> = emptyList()
)