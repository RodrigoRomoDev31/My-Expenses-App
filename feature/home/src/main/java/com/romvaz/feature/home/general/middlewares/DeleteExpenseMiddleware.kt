package com.romvaz.feature.home.general.middlewares

import com.core.store.ActionSideEffect
import com.romvaz.feature.home.general.GeneralExpensesScreenAction
import com.romvaz.feature.home.general.GeneralExpensesUiState
import com.romvaz.room.databases.expenses.ExpensesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DeleteExpenseMiddleware @Inject constructor(
    private val expensesDao: ExpensesDao
) : ActionSideEffect<GeneralExpensesUiState, GeneralExpensesScreenAction>() {

    override fun observe(
        actionsFlow: Flow<GeneralExpensesScreenAction>,
        currentState: () -> GeneralExpensesUiState
    ): Flow<GeneralExpensesScreenAction> =
        actionsFlow
            .filter { it is GeneralExpensesScreenAction.OnDeleteExpense }
            .map {
                expensesDao.deleteTest(currentState().expensesRoomModel!!)
            }
            .map {
                GeneralExpensesScreenAction.OnExpenseDeleted
            }
}

