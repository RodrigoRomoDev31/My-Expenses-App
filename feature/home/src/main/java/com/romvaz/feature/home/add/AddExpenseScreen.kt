package com.romvaz.feature.home.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.core.ui.R
import com.core.ui.components.ButtonComponent
import com.core.ui.components.ButtonStyle
import com.core.ui.components.ExpensesAppHeader
import com.core.ui.components.ExpensesAppScaffold
import com.core.ui.components.InputComponent
import com.core.ui.components.SnackBarTopComponent
import com.core.ui.components.SnackBarTopStatus
import com.core.ui.components.SnackBarVisuals
import com.core.ui.components.VerticalSpacer
import com.core.ui.theme.Spacings
import com.core.ui.theme.TypographyExtensions.captionsBold
import com.core.ui.theme.TypographyExtensions.h3
import com.core.ui.utils.ExpensesTypes
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
        disclaimerShowedState = state.disclaimerShowed,
        counterState = state.counter,
        id = state.id,
        onAmountChange = { viewModel.updateAmount(it) },
        onChipSelected = { viewModel.updateChipsState(it) },
        saveExpense = viewModel::saveExpense,
        navigateBack = viewModel::navigateBack
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
    disclaimerShowedState: Boolean,
    counterState: Int,
    id: Int,
    onAmountChange: (String) -> Unit,
    onChipSelected: (String) -> Unit,
    saveExpense: () -> Unit,
    navigateBack: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_add_animation))

    LaunchedEffect(key1 = counterState) {
        val message = if (id == 0) R.string.expense_saved else R.string.expense_update
        if (counterState > 0) {
            snackBarHostState.showSnackbar(
                SnackBarVisuals(
                    message = context.getString(message),
                    withDismissAction = true
                )
            )
        }

        if (id != 0)
            navigateBack()
    }

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
                        navigateBack()
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
        },
        snackbarHost = {
            SnackBarTopComponent(
                hostState = snackBarHostState,
                snackBarTopStatus = SnackBarTopStatus.SUCCESS,
                onClickAction = { snackBarHostState.currentSnackbarData?.dismiss() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(Spacings.four)

            InputComponent(
                value = amountState,
                onValueChange = { onAmountChange(it) },
                placeholder = stringResource(R.string.amount)
            )

            VerticalSpacer(Spacings.four)

            LazyRow(
                contentPadding = PaddingValues(horizontal = Spacings.six),
                horizontalArrangement = Arrangement.spacedBy(Spacings.three),
                modifier = Modifier.weight(1f)
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

            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .size(300.dp)
                    .scale(1f),
                isPlaying = true,
                iterations = LottieConstants.IterateForever,
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.Fit
            )

            if (disclaimerShowedState)
                Text(
                    text = stringResource(R.string.save_disclaimer),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Spacings.six),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.captionsBold.copy(Color.Red)
                )

            ButtonComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacings.six, vertical = Spacings.two),
                onClick = {
                    saveExpense()
                },
                text = {
                    Text(
                        text = stringResource(id = R.string.btn_add_expenses)
                    )
                },
                style = ButtonStyle.Primary,
                enabled = !disclaimerShowedState
            )
        }
    }
}
