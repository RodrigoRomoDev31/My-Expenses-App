package com.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.core.ui.theme.Spacings
import com.core.ui.theme.TypographyExtensions.captions
import com.core.ui.theme.TypographyExtensions.captionsBold
import com.core.ui.theme.myExpensesAppColors

@Composable
fun SnackBarComponent(
    hostState: SnackbarHostState,
    snackBarCallback: () -> Unit = {}
) {
    SnackbarHost(
        hostState = hostState,
        snackbar = { data ->

            val snackBarVisuals = data.visuals as? SnackBarVisuals

            Snackbar(
                contentColor = Color.White,
                containerColor = MaterialTheme.myExpensesAppColors.Gray20.copy(1f),
                shape = RoundedCornerShape(Spacings.two),
                action = {
                    if (snackBarVisuals?.withDismissAction == true) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = Spacings.six)
                                .size(20.dp)
                                .clickable { hostState.currentSnackbarData?.dismiss() },
                            tint = MaterialTheme.myExpensesAppColors.White
                        )
                    }
                },
                content = {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .padding(
                                horizontal = Spacings.six
                            )
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = snackBarVisuals?.title!!,
                            style = MaterialTheme.typography.captionsBold.copy(MaterialTheme.myExpensesAppColors.White),
                            modifier = Modifier.weight(1f)
                        )

                        ProvideTextStyle(
                            value = MaterialTheme.typography.captions.copy(
                                color = MaterialTheme.myExpensesAppColors.White
                            )
                        ) {
                            Text(
                                text = data.visuals.message,
                                modifier = Modifier.clickable {
                                    snackBarCallback()
                                },
                                textDecoration = TextDecoration.Underline
                            )

                        }
                    }
                }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}

data class SnackBarVisuals(
    override val message: String,
    override val withDismissAction: Boolean = false,
    override val actionLabel: String = "",
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    val title: String,
) : SnackbarVisuals

