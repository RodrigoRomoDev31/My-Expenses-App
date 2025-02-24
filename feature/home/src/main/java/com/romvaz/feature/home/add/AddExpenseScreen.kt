package com.romvaz.feature.home.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.ui.R
import com.core.ui.components.InputComponent
import com.core.ui.components.VerticalSpacer
import com.core.ui.theme.Spacings
import com.core.ui.theme.TypographyExtensions.h3
import com.core.ui.utils.ExpensesTypes
import com.romvaz.core.ui.components.ExpensesAppHeader
import com.romvaz.core.ui.components.ExpensesAppScaffold
import com.romvaz.feature.home.add.components.ExpenseTypeChipComponent

@Composable
fun AddExpenseScreen(
    viewModel: AddExpensesViewModel = hiltViewModel()
) {
    val state by viewModel.observe().collectAsStateWithLifecycle()

    Content(
        entertainmentChipState = state.entertainmentChip,
        foodChipState = state.foodChip,
        streamingChipState = state.streamingChip,
        stationaryChipState = state.stationaryChip,
        homeLoanChipState = state.homeLoanChip,
        personalLoanChipState = state.personalLoanChip,
        amountState = state.amount,
        onAmountChange = { viewModel.updateAmount(it) },
        onChipSelected = { viewModel.updateChipsState(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    entertainmentChipState: Boolean,
    foodChipState: Boolean,
    streamingChipState: Boolean,
    stationaryChipState: Boolean,
    homeLoanChipState: Boolean,
    personalLoanChipState: Boolean,
    amountState: String,
    onAmountChange: (String) -> Unit,
    onChipSelected: (String) -> Unit
) {
    ExpensesAppScaffold(
        header = {
            ExpensesAppHeader(
                title = {
                    Text(
                        text = stringResource(R.string.add_expenses),
                        style = MaterialTheme.typography.h3.copy(MaterialTheme.colorScheme.onSurface)
                    )
                },
                primaryAction = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(Spacings.four)

            InputComponent(
                value = amountState,
                onValueChange = { onAmountChange(it) },
                placeholder = stringResource(R.string.ammount)
            )

            VerticalSpacer(Spacings.four)

            LazyRow(
                contentPadding = PaddingValues(horizontal = Spacings.six),
                horizontalArrangement = Arrangement.spacedBy(Spacings.three)
            ) {
                item {
                    ExpenseTypeChipComponent(
                        text = ExpensesTypes.Entertainment.type,
                        selected = entertainmentChipState,
                        onClick = { onChipSelected(ExpensesTypes.Entertainment.type) }
                    )
                }
                item {
                    ExpenseTypeChipComponent(
                        text = ExpensesTypes.Food.type,
                        selected = foodChipState,
                        onClick = { onChipSelected(ExpensesTypes.Food.type) }
                    )
                }
                item {
                    ExpenseTypeChipComponent(
                        text = ExpensesTypes.StreamingServices.type,
                        selected = streamingChipState,
                        onClick = { onChipSelected(ExpensesTypes.StreamingServices.type) }
                    )
                }
                item {
                    ExpenseTypeChipComponent(
                        text = ExpensesTypes.Stationary.type,
                        selected = stationaryChipState,
                        onClick = { onChipSelected(ExpensesTypes.Stationary.type) }
                    )
                }
                item {
                    ExpenseTypeChipComponent(
                        text = ExpensesTypes.HomeLoan.type,
                        selected = homeLoanChipState,
                        onClick = { onChipSelected(ExpensesTypes.HomeLoan.type) }
                    )
                }
                item {
                    ExpenseTypeChipComponent(
                        text = ExpensesTypes.PersonalLoan.type,
                        selected = personalLoanChipState,
                        onClick = { onChipSelected(ExpensesTypes.PersonalLoan.type) }
                    )
                }
            }
        }
    }
}