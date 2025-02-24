package com.romvaz.feature.home.add.middlewares

import com.core.store.ActionSideEffect
import com.romvaz.feature.home.add.AddExpensesScreenAction
import com.romvaz.feature.home.add.AddExpensesUiState
import com.romvaz.room.databases.expenses.ExpensesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetExpenseMiddleware @Inject constructor(
    private val expensesDao: ExpensesDao
) : ActionSideEffect<AddExpensesUiState, AddExpensesScreenAction>() {

    override fun observe(
        actionsFlow: Flow<AddExpensesScreenAction>,
        currentState: () -> AddExpensesUiState
    ): Flow<AddExpensesScreenAction> =
        actionsFlow
            .filter { it is AddExpensesScreenAction.OnGetExpense }
            .distinctUntilChanged()
            .map {
                expensesDao.getExpenseById(currentState().id)
            }
            .map {
                if (it != null)
                    AddExpensesScreenAction.OnExpenseFetch(it)
                else
                    AddExpensesScreenAction.OnNothing
            }
}

