package com.romvaz.feature.home.general.middlewares

import com.core.store.StateSideEffect
import com.romvaz.feature.home.general.GeneralExpensesScreenAction
import com.romvaz.feature.home.general.GeneralExpensesUiState
import com.romvaz.room.databases.expenses.ExpensesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetUserExpensesMiddleware @Inject constructor(
    private val expensesDao: ExpensesDao
) : StateSideEffect<GeneralExpensesUiState, GeneralExpensesScreenAction>() {

    override fun observe(stateFlow: Flow<GeneralExpensesUiState>): Flow<GeneralExpensesScreenAction> =
        expensesDao
            .getTestSortedById()
            .distinctUntilChanged()
            .map { GeneralExpensesScreenAction.OnGetUserExpenses(it) }
}
