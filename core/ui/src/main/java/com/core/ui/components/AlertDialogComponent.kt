package com.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.core.ui.theme.Spacings
import com.core.ui.theme.TypographyExtensions.captions
import com.core.ui.theme.TypographyExtensions.h4
import com.core.ui.theme.TypographyExtensions.utilityUppercaseBold
import com.core.ui.theme.isDarkTheme
import com.core.ui.theme.myExpensesAppColors

@Composable
fun AlertDialogComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    message: String,
    textConfirmButton: String,
    textCancelButton: String = "",
    onDismissRequest: () -> Unit = {},
    onClickDismiss: () -> Unit = {},
    onClickConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        title = {
            if (title.isNotEmpty())
                Text(text = title, style = MaterialTheme.typography.h4)
        },
        text = {
            Text(text = message, style = MaterialTheme.typography.captions)
        },
        confirmButton = {
            Text(
                text = textConfirmButton,
                style = MaterialTheme.typography.utilityUppercaseBold,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = Spacings.three)
                    .clickable {
                        onClickConfirm()
                    }
            )
        },
        dismissButton = {
            if (textCancelButton.isNotEmpty())
                Text(
                    text = textCancelButton,
                    style = MaterialTheme.typography.utilityUppercaseBold,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = Spacings.three)
                        .clickable {
                            onClickDismiss()
                        },
                    color = MaterialTheme.myExpensesAppColors.notificationError
                )
        },
        tonalElevation = 0.dp,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = if (isDarkTheme()) {
            MaterialTheme.myExpensesAppColors.Gray20
        } else {
            MaterialTheme.myExpensesAppColors.Gray50
        },
        containerColor = if (isDarkTheme()) {
            MaterialTheme.myExpensesAppColors.Black
        } else {
            MaterialTheme.myExpensesAppColors.White
        }
    )
}
