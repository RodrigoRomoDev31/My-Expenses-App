package com.romvaz.feature.home.add.middlewares

import com.core.domain.model.room.ExpensesRoomModel
import com.core.store.ActionSideEffect
import com.core.ui.utils.getCurrentTimestamp
import com.romvaz.feature.home.add.AddExpensesScreenAction
import com.romvaz.feature.home.add.AddExpensesUiState
import com.romvaz.room.databases.expenses.ExpensesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class AddExpenseMiddleware @Inject constructor(
    private val expensesDao: ExpensesDao
) : ActionSideEffect<AddExpensesUiState, AddExpensesScreenAction>() {

    override fun observe(
        actionsFlow: Flow<AddExpensesScreenAction>,
        currentState: () -> AddExpensesUiState
    ): Flow<AddExpensesScreenAction> =
        actionsFlow
            .filter { it is AddExpensesScreenAction.OnSaveExpense }
            .map {
                expensesDao.upsertTest(
                    if (currentState().id == 0)
                        ExpensesRoomModel(
                            expenseType = currentState().type,
                            expense = currentState().amount,
                            date = getCurrentTimestamp()
                        )
                    else
                        ExpensesRoomModel(
                            id = currentState().id,
                            expenseType = currentState().type,
                            expense = currentState().amount,
                            date = currentState().time
                        )
                )
            }
            .map {
                AddExpensesScreenAction.OnSaved
            }
}

