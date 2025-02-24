package com.romvaz.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

/**
 * @param modifier Modifier to be applied to the layout, allowing customization of spacing, padding,
 * etc. Default is `Modifier`.
 * @param containerColor The background color of the container. Default is `MaterialTheme.colorScheme.surface`.
 * @param header A composable function representing the header content of the layout. Default is an
 * empty composable `{}`.
 * @param bottomBar A composable function representing the bottom bar content of the layout. Default
 * is an empty composable `{}`.
 * @param snackbarHost A composable function to display a Snackbar host, used for showing Snackbar
 * messages. Default is an empty composable `{}`.
 * @param content A composable function that represents the main content of the layout. It receives
 * `PaddingValues` to properly handle insets and padding.
 */
@Composable
fun ExpensesAppScaffold(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    header: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = header,
        snackbarHost = snackbarHost,
        containerColor = containerColor,
        bottomBar = bottomBar,
        content = content,
    )
}


/**
 * @param modifier Modifier to be applied to the top app bar for layout customization. Default is `Modifier`.
 * @param title A composable function that represents the title content of the app bar.
 * Default is an empty composable `{}`.
 * @param primaryAction A composable function for the primary action, usually an icon button
 * (e.g., a back button). Default is an empty composable `{}`.
 * @param secondaryActions A composable function within `RowScope`, allowing multiple secondary
 * actions (e.g., menu items, icons). Default is an empty composable `{}`.
 * @param scrollBehavior Optional scroll behavior to control how the top app bar reacts to scrolling
 * (e.g., collapsing, elevation changes). Default is `null`.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesAppHeader(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    primaryAction: @Composable () -> Unit = {},
    secondaryActions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = {
            title()
        },
        navigationIcon = { primaryAction() },
        actions = {

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                secondaryActions()
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}


/**
 * @param modifier Modifier to be applied to the component for layout customization. Default is `Modifier`.
 * @param icon An optional `Painter` used to display an icon in the component. Default is `null`.
 * @param iconDescription A string description for the icon, used for accessibility (e.g., screen readers).
 * Default is `null`.
 * @param primaryAction A composable function representing the primary action, typically an icon button.
 * Default is an empty composable `{}`.
 * @param secondaryActions A composable function within `RowScope`, allowing multiple secondary actions
 * Default is an empty composable `{}`.
 * @param scrollBehavior Optional scroll behavior defining how the component reacts to scrolling
 * (e.g., collapsing or elevation changes). Default is `null`.
 * @param iconTint The color applied to the icon. If `Color.Unspecified`, it uses the default system-defined tint.
 * Default is `Color.Unspecified`.
 */
@Suppress("LongParameterList")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesAppTransparentHeader(
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    iconDescription: String? = null,
    primaryAction: @Composable () -> Unit = {},
    secondaryActions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null,
    iconTint: Color = Color.Unspecified
) {
    Box {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
        )

        CenterAlignedTopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    icon?.let {
                        Icon(
                            painter = icon,
                            contentDescription = iconDescription,
                            tint = iconTint
                        )
                    }
                }
            },
            navigationIcon = { primaryAction() },
            actions = {

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    secondaryActions()
                }

            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White
            ),
            scrollBehavior = scrollBehavior,
            modifier = modifier
        )
    }
}
