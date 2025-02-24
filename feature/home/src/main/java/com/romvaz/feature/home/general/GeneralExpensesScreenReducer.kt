package com.romvaz.feature.home.general

import androidx.compose.ui.graphics.Color
import co.yml.charts.ui.piechart.models.PieChartData
import com.core.domain.model.room.ExpensesRoomModel
import com.core.store.Reducer
import com.core.ui.utils.getExpenseMonth


class GeneralExpensesScreenReducer : Reducer<GeneralExpensesUiState, GeneralExpensesScreenAction> {

    override fun reduce(
        state: GeneralExpensesUiState,
        action: GeneralExpensesScreenAction
    ): GeneralExpensesUiState =
        when (action) {
            is GeneralExpensesScreenAction.OnGetUserExpenses -> state.onGetUserExpenses(action)
        }

    private fun GeneralExpensesUiState.onGetUserExpenses(
        action: GeneralExpensesScreenAction.OnGetUserExpenses
    ): GeneralExpensesUiState {
        val listByMonth = action.expenses.groupBy { getExpenseMonth(it.date) }
        val listOfData = mutableListOf<PieChartData.Slice>()

        val listByMonthType = listByMonth.mapValues { (_, expenses) ->
            expenses.groupBy { it.expenseType }
                .mapValues { (_, grouped) -> grouped.sumOf { it.expense.toDouble() } }
        }

        var total = 0.0

        listByMonth.values.forEach {
            it.forEach { model ->
                total += model.expense.toDouble()
            }
        }

        listByMonthType.entries.first().value.entries.forEach { data ->
            listOfData.add(
                PieChartData.Slice(
                    data.key,
                    ((data.value / total) * 100).toFloat(),
                    Color.Cyan
                )
            )
        }


        return this.copy(
            expensesList = action.expenses,
            expensesByMonth = listByMonth,
            totalExpenses = total,
            expensesByMonthAndType = listByMonthType,
            monthListSize = listByMonth.entries.size,
            chartData = listOfData
        )
    }
}


sealed interface GeneralExpensesScreenAction {

    data class OnGetUserExpenses(
        val expenses: List<ExpensesRoomModel>
    ) : GeneralExpensesScreenAction
}
