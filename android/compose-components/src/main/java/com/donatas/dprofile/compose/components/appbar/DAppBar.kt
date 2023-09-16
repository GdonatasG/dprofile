package com.donatas.dprofile.compose.components.appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.donatas.dprofile.compose.theme.OpenSans
import com.donatas.dprofile.compose.theme.Oswald

@Composable
fun AppBarTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(
  /*          fontSize = 18.sp,*/
            fontFamily = OpenSans,
            fontWeight = FontWeight.SemiBold
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun DAppBar(
    title: String = "",
    centerTitle: Boolean = true,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    color: Color = MaterialTheme.colorScheme.background
) {
    val titleText: @Composable () -> Unit = {
        AppBarTitle(title = title)
    }

    AppBar(
        title = titleText,
        centerTitle = centerTitle,
        navigationIcon = navigationIcon,
        actions = actions,
        color = color
    )
}

@Composable
fun DAppBar(
    title: @Composable () -> Unit = {},
    centerTitle: Boolean = true,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    color: Color = MaterialTheme.colorScheme.background
) {
    AppBar(
        title = title,
        centerTitle = centerTitle,
        navigationIcon = navigationIcon,
        actions = actions,
        color = color
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    title: @Composable () -> Unit = {},
    centerTitle: Boolean = true,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    color: Color = MaterialTheme.colorScheme.background
) {
    val titleText: @Composable () -> Unit = {
        title()
    }

    if (centerTitle) {
        CenterAlignedTopAppBar(
            title = titleText,
            navigationIcon = navigationIcon,
            actions = actions,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = color)
        )

        return
    }

    TopAppBar(
        title = titleText,
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = color)
    )
}
