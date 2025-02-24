package com.romvaz.feature.home.general

import co.yml.charts.ui.piechart.models.PieChartData
import com.core.ui.utils.getColorForExpenseType
import com.core.ui.utils.getExpenseMonth

fun GeneralExpensesUiState.onGetUserExpenses(
    action: GeneralExpensesScreenAction.OnGetUserExpenses
): GeneralExpensesUiState {
    if (action.expenses.isNotEmpty()) {
        val expensesByMonth = action.expenses.groupBy { getExpenseMonth(it.date) }.toList()
        var total = 0.0
        val chartData = mutableListOf<PieChartData.Slice>()

        expensesByMonth[index].second.forEach { expense ->
            total += expense.expense.toDouble()
        }

        expensesByMonth[index].second.forEach { expenseModel ->
            chartData.add(
                PieChartData.Slice(
                    expenseModel.expenseType,
                    ((expenseModel.expense.toDouble() / total) * 100).toFloat(),
                    getColorForExpenseType(expenseModel.expenseType)
                )
            )
        }

        val expensesByType = expensesByMonth[index].second.groupBy { it.expenseType }.toList()

        return this.copy(
            expensesList = action.expenses,
            expensesByMonth = expensesByMonth,
            totalExpenses = total,
            chartData = chartData,
            expensesByType = expensesByType
        )
    } else
        return this
}

fun GeneralExpensesUiState.onChangeMonth(
    action: GeneralExpensesScreenAction.OnChangeMonth
): GeneralExpensesUiState {
    val newIndex = if (action.forward) index + 1 else index - 1
    var newTotal = 0.0
    val newChartData = mutableListOf<PieChartData.Slice>()

    expensesByMonth[newIndex].second.forEach { expense ->
        newTotal += expense.expense.toDouble()
    }

    expensesByMonth[newIndex].second.forEach { expenseModel ->
        newChartData.add(
            PieChartData.Slice(
                expenseModel.expenseType,
                ((expenseModel.expense.toDouble() / newTotal) * 100).toFloat(),
                getColorForExpenseType(expenseModel.expenseType)
            )
        )
    }

    val expensesByType = expensesByMonth[newIndex].second.groupBy { it.expenseType }.toList()

    return this.copy(
        index = newIndex,
        totalExpenses = newTotal,
        chartData = newChartData,
        expensesByType = expensesByType
    )
}