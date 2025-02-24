package com.romvaz.feature.home.add.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.core.ui.theme.myExpensesAppColors

@Composable
fun ExpenseTypeChipComponent(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        onClick = { if (!selected) onClick() },
        label = { Text(text) },
        selected = selected,
        leadingIcon = {
            if (selected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize),
                    tint = MaterialTheme.myExpensesAppColors.White
                )
            }
        },
        colors = SelectableChipColors(
            containerColor = MaterialTheme.colorScheme.surface,
            labelColor = MaterialTheme.colorScheme.onSurface,
            leadingIconColor = MaterialTheme.myExpensesAppColors.White,
            trailingIconColor = MaterialTheme.myExpensesAppColors.White,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledLabelColor = MaterialTheme.colorScheme.onSurface,
            disabledLeadingIconColor = MaterialTheme.colorScheme.surface,
            disabledTrailingIconColor = MaterialTheme.colorScheme.surface,
            selectedContainerColor = MaterialTheme.myExpensesAppColors.Primary100,
            disabledSelectedContainerColor = MaterialTheme.colorScheme.surface,
            selectedLabelColor = MaterialTheme.myExpensesAppColors.White,
            selectedLeadingIconColor = MaterialTheme.myExpensesAppColors.White,
            selectedTrailingIconColor = MaterialTheme.myExpensesAppColors.White
        )
    )
}