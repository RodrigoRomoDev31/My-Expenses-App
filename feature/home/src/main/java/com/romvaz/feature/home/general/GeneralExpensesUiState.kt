package com.romvaz.feature.home.general

import co.yml.charts.ui.piechart.models.PieChartData
import com.core.domain.model.room.ExpensesRoomModel

data class GeneralExpensesUiState (
    val expensesList: List<ExpensesRoomModel> = emptyList(),
    val expensesByMonth: List<Pair<String, List<ExpensesRoomModel>>> = emptyList(),
    val chartData: MutableList<PieChartData.Slice> = mutableListOf(),
    val expensesByType: List<Pair<String, List<ExpensesRoomModel>>> = emptyList(),
    val totalExpenses: Double = 0.0,
    val index: Int = 0,
    val expensesRoomModel: ExpensesRoomModel? = null,
    val counter: Int = 0
)