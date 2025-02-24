package com.romvaz.feature.home.general.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.core.ui.R
import com.core.ui.components.ButtonComponent
import com.core.ui.components.ButtonStyle
import com.core.ui.components.VerticalSpacer
import com.core.ui.theme.Spacings
import com.core.ui.theme.TypographyExtensions.captions
import com.core.ui.theme.TypographyExtensions.h2
import com.core.ui.theme.isDarkTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun NoExpensesComponent(
    modifier: Modifier,
    addNewExpense: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_general_animation))

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(Spacings.six),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            VerticalSpacer(height = 132.dp)

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

            Text(
                text = stringResource(id = R.string.no_expenses_tittle),
                style = MaterialTheme.typography.h2.copy(MaterialTheme.colorScheme.onSurface),
                modifier = Modifier.padding(bottom = Spacings.three),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.no_expenses_disclaimer),
                style = MaterialTheme.typography.captions.copy(MaterialTheme.colorScheme.onSurface),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = Spacings.six)
            ) {
                ButtonComponent(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        addNewExpense()
                    },
                    text = {
                        Text(
                            text = stringResource(id = R.string.btn_add_expenses)
                        )
                    },
                    style = ButtonStyle.Primary
                )
            }
        }
    }

    rememberSystemUiController().setStatusBarColor(
        color = Color.Transparent,
        darkIcons = !isDarkTheme()
    )
}