package com.donatas.dprofile.compose.components.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.appbar.AppBarHeader
import com.donatas.dprofile.compose.components.extension.calculateScrollOffset

@Composable
fun AppScaffoldWithCollapsibleHeader(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    lazyListState: LazyListState,
    header: AppBarHeader,
    appBar: @Composable () -> Unit,
    tabBar: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val headerHeightPx = with(density) { header.headerHeight.toPx() }
    val toolbarHeightPx = with(density) { 56.dp.toPx() }

    Surface(
        modifier = modifier
            .background(color = backgroundColor)
            .statusBarsPadding(),
        color = backgroundColor,
        contentColor = contentColorFor(backgroundColor)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            header.Compose()
            Body(
                headerHeight = header.headerHeight,
                content = content
            )
            ToolBar(
                lazyListState = lazyListState,
                headerHeightPx = headerHeightPx,
                appBar = appBar
            )
        }
    }
}

@Composable
private fun Body(
    headerHeight: Dp,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(headerHeight))
        content()
    }
}

@Composable
private fun ToolBar(
    lazyListState: LazyListState,
    headerHeightPx: Float,
    appBar: @Composable () -> Unit
) {
    var toolbarHeightPx by remember { mutableIntStateOf(0) }

    val toolbarBottom by remember {
        mutableFloatStateOf(headerHeightPx - toolbarHeightPx)
    }

    val showToolbar by remember {
        derivedStateOf {
            val scrollOffset = lazyListState.calculateScrollOffset(additionalHeightInPx = headerHeightPx)
            println("scrollOffset: $scrollOffset")
            scrollOffset >= toolbarBottom
        }
    }

    AnimatedVisibility(
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        Box(modifier = Modifier.onGloballyPositioned {
            toolbarHeightPx = it.size.height
        }) {
            appBar()
        }
    }
}

