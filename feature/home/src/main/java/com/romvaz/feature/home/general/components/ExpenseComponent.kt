package com.romvaz.feature.home.general.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.core.domain.model.room.ExpensesRoomModel
import com.core.ui.theme.Spacings
import com.core.ui.theme.TypographyExtensions.extraSmall
import com.core.ui.theme.TypographyExtensions.labels
import com.core.ui.utils.getColorForExpenseType
import com.core.ui.utils.getExpenseDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpenseComponent(
    expense: ExpensesRoomModel,
    navigateToEdit: (Int) -> Unit,
    onDelete: (ExpensesRoomModel) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(Spacings.four)
            )
            .border(
                width = 2.dp,
                color = getColorForExpenseType(expense.expenseType),
                shape = RoundedCornerShape(Spacings.four)
            )
            .combinedClickable(
                onClick = { navigateToEdit(expense.id) },
                onLongClick = { onDelete(expense) }
            )
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(Spacings.four)) {

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Amount: $${expense.expense}",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.labels
                )

                Text(
                    text = getExpenseDate(expense.date),
                    style = MaterialTheme.typography.extraSmall
                )
            }
        }
    }
}