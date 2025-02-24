package com.romvaz.feature.home.general

import co.yml.charts.ui.piechart.models.PieChartData
import com.core.domain.model.room.ExpensesRoomModel

data class GeneralExpensesUiState (
    val expensesList: List<ExpensesRoomModel> = emptyList(),
    val expensesByMonth: Map<String, List<ExpensesRoomModel>> = emptyMap(),
    val expensesByMonthAndType: Map<String, Map<String, Double>> = emptyMap(),
    val totalExpenses: Double = 0.0,
    val chartData: MutableList<PieChartData.Slice> = mutableListOf(),
    val monthListSize: Int = 0,
    val pieChartData: PieChartData? = null
)