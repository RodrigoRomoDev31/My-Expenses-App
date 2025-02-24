package com.romvaz.feature.home.general

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.domain.model.room.ExpensesRoomModel
import com.core.ui.components.SnackBarTopComponent
import com.core.ui.components.SnackBarTopStatus
import com.romvaz.core.ui.components.ExpensesAppScaffold
import com.romvaz.core.ui.components.ExpensesAppTransparentHeader
import com.romvaz.feature.home.R
import com.romvaz.feature.home.general.components.NoExpensesComponent

@Composable
fun GeneralExpensesScreen(
    viewModel: GeneralExpensesViewModel = hiltViewModel()
) {
    val state by viewModel.observe().collectAsStateWithLifecycle()

    Content(
        expensesListState = state.expensesList,
        navigateToAdd = viewModel::navigateToAdd
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    expensesListState: List<ExpensesRoomModel>,
    navigateToAdd: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    ExpensesAppScaffold(
        header = {
            ExpensesAppTransparentHeader(
                icon = painterResource(R.drawable.ic_devtek_logo),
                iconTint = MaterialTheme.colorScheme.onSurface,
            )
        },
        snackbarHost = {
            SnackBarTopComponent(
                hostState = snackBarHostState,
                snackBarTopStatus = SnackBarTopStatus.SUCCESS,
                onClickAction = { snackBarHostState.currentSnackbarData?.dismiss() }
            )
        }
    ) { paddingValues ->
        if (expensesListState.isEmpty())
            NoExpensesComponent(
                modifier = Modifier.padding(paddingValues),
                addNewExpense = navigateToAdd
            )

    }
}