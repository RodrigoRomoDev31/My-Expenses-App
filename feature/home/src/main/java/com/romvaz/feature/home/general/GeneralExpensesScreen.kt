package com.romvaz.feature.home.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.core.domain.model.room.ExpensesRoomModel
import com.core.ui.components.SnackBarTopComponent
import com.core.ui.components.SnackBarTopStatus
import com.core.ui.components.VerticalSpacer
import com.core.ui.theme.Spacings
import com.core.ui.theme.TypographyExtensions.h3
import com.core.ui.theme.TypographyExtensions.h4
import com.core.ui.theme.myExpensesAppColors
import com.romvaz.core.ui.components.ExpensesAppScaffold
import com.romvaz.core.ui.components.ExpensesAppTransparentHeader
import com.romvaz.feature.home.R
import com.romvaz.feature.home.general.components.ExpenseComponent
import com.romvaz.feature.home.general.components.NoExpensesComponent

@Composable
fun GeneralExpensesScreen(
    viewModel: GeneralExpensesViewModel = hiltViewModel()
) {
    val state by viewModel.observe().collectAsStateWithLifecycle()

    Content(
        expensesListState = state.expensesList,
        expensesByMonthAndType = state.expensesByMonthAndType,
        expensesByMonth = state.expensesByMonth,
        chatDataState = state.chartData,
        totalState = state.totalExpenses,
        navigateToAdd = viewModel::navigateToAdd
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    expensesListState: List<ExpensesRoomModel>,
    expensesByMonthAndType: Map<String, Map<String, Double>>,
    expensesByMonth: Map<String, List<ExpensesRoomModel>>,
    chatDataState: MutableList<PieChartData.Slice>,
    totalState: Double,
    navigateToAdd: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val donutChartConfig = PieChartConfig(
        strokeWidth = 60f,
        activeSliceAlpha = .7f,
        isAnimationEnable = true,
        showSliceLabels = true,
        backgroundColor = MaterialTheme.colorScheme.surface,
        labelVisible = true,
        sliceLabelTextColor = MaterialTheme.colorScheme.onSurface,
        labelColor = MaterialTheme.colorScheme.onSurface,
    )

    ExpensesAppScaffold(
        header = {
            ExpensesAppTransparentHeader(
                icon = painterResource(R.drawable.ic_logo),
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
        else
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = Spacings.six, vertical = Spacings.two),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = expensesByMonthAndType.entries.first().key,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.h3.copy(MaterialTheme.myExpensesAppColors.Primary100),
                        textAlign = TextAlign.Center
                    )

                    VerticalSpacer(Spacings.four)

                    Text(
                        text = "Total: $$totalState",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.h4.copy(MaterialTheme.colorScheme.onSurface),
                        textAlign = TextAlign.Center
                    )

                    VerticalSpacer(Spacings.six)

                    if (chatDataState.isNotEmpty())
                        DonutPieChart(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surface)
                                .size(200.dp),
                            pieChartData = PieChartData(
                                chatDataState,
                                PlotType.Donut
                            ),
                            pieChartConfig = donutChartConfig
                        )

                    VerticalSpacer(Spacings.four)

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            Text(
                                text = stringResource(com.core.ui.R.string.expenses_tittle),
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.h4.copy(MaterialTheme.colorScheme.onSurface),
                                textAlign = TextAlign.Start
                            )
                        }

                        item {
                            VerticalSpacer(Spacings.four)
                        }
                        expensesByMonth.forEach { (_, expenses) ->

                            items(expenses) { expense ->
                                ExpenseComponent(expense)
                            }
                        }
                    }

                }
            }

    }
}