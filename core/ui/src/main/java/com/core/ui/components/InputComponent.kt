package com.core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.core.ui.theme.Spacings
import com.core.ui.theme.TypographyExtensions.captions
import com.core.ui.theme.isDarkTheme
import com.core.ui.theme.myExpensesAppColors


@Composable
fun InputComponent(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    singleLine: Boolean = true
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        value = value,
        onValueChange = {
            val filteredValue = it.filter { char -> char.isDigit() || char == '.' }
            if (filteredValue.count { it == '.' } <= 1) {
                onValueChange(filteredValue)
            }
        },
        modifier = Modifier
            .padding(horizontal = Spacings.six)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(Spacings.two)
            )
            .focusRequester(focusRequester)
            .focusable(false),
        visualTransformation = VisualTransformation.None,
        textStyle = TextStyle(fontStyle = FontStyle.Normal),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.captions
                    .copy(
                        if (isDarkTheme()) MaterialTheme.myExpensesAppColors.Gray20
                        else MaterialTheme.myExpensesAppColors.Gray40
                    )
            )
        },
        singleLine = singleLine,
        shape = RoundedCornerShape(8.dp),
        colors = getColors(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )

        }
    )
}

// CONFIGURE COLORS DEPENDING IF IS DARK THEME
@Composable
private fun getColors(): TextFieldColors =
    TextFieldDefaults.colors(
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        focusedContainerColor = MaterialTheme.colorScheme.surface,
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        cursorColor = MaterialTheme.myExpensesAppColors.Primary80,
        unfocusedLeadingIconColor = MaterialTheme.myExpensesAppColors.Gray20,
        focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        focusedLabelColor = if (isDarkTheme()) {
            MaterialTheme.myExpensesAppColors.Gray20
        } else {
            MaterialTheme.myExpensesAppColors.Gray40
        },
        unfocusedLabelColor = if (isDarkTheme()) {
            MaterialTheme.myExpensesAppColors.Gray20
        } else {
            MaterialTheme.myExpensesAppColors.Gray60
        }
    )
