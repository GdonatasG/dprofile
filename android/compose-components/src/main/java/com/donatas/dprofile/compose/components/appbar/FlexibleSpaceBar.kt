package com.donatas.dprofile.compose.components.appbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FlexibleSpaceBar(
    title: String = "",
    centerTitle: Boolean = true,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    headerHeight: Dp,
    scrollState: ScrollState,
    color: Color = MaterialTheme.colorScheme.background
) {
    val density = LocalDensity.current
    val headerHeightPx = with(density) { headerHeight.toPx() }
    val appBarHeightPx = with(density) { 56.dp.toPx() }

    val toolbarBottom by remember {
        mutableFloatStateOf(headerHeightPx - appBarHeightPx)
    }

    val showToolbar by remember {
        derivedStateOf {
            scrollState.value >= toolbarBottom
        }
    }


}
