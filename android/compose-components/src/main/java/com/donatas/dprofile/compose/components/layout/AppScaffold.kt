package com.donatas.dprofile.compose.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.dp

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    appBar: @Composable (() -> Unit)? = null,
    tabBar: @Composable (() -> Unit)? = null,
    snackBar: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .background(color = backgroundColor)
            .statusBarsPadding(),
        color = backgroundColor,
        contentColor = contentColorFor(backgroundColor)
    ) {
        val topBar: @Composable () -> Unit =
            {
                if (appBar == null) {
                    Box(modifier = Modifier.size(0.dp))
                } else {
                    appBar()
                }
            }

        val tabLayout: @Composable () -> Unit = {
            if (tabBar == null) {
                Box(modifier = Modifier.size(0.dp))
            } else {
                Surface(color = MaterialTheme.colorScheme.background) {
                    tabBar()
                }
            }
        }

        val snackBarLayout: @Composable () -> Unit = {
            snackBar?.let {
                it()
            }
        }

        SubcomposeLayout { constraints ->
            val layoutWidth = constraints.maxWidth
            val layoutHeight = constraints.maxHeight
            val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

            layout(layoutWidth, layoutHeight) {
                val topBarPlaceables = subcompose(AppScaffoldContent.TOP_BAR, topBar).map {
                    it.measure(looseConstraints)
                }

                val tabBarPlaceables = subcompose(AppScaffoldContent.TAB_BAR, tabLayout).map {
                    it.measure(looseConstraints)
                }

                val snackBarPlaceables = subcompose(AppScaffoldContent.SNACK_BAR, snackBarLayout).map {
                    it.measure(looseConstraints)
                }

                val topBarHeight = topBarPlaceables.maxByOrNull { it.height }?.height ?: 0
                val tabBarHeight = tabBarPlaceables.maxByOrNull { it.height }?.height ?: 0
                val snackBarHeight = snackBarPlaceables.maxByOrNull { it.height }?.height ?: 0
                val bodyContentHeight = layoutHeight - topBarHeight - tabBarHeight

                val bodyContentPlaceables = subcompose(AppScaffoldContent.CONTENT) {
                    content()
                }.map { it.measure(looseConstraints.copy(maxHeight = bodyContentHeight)) }

                bodyContentPlaceables.forEach {
                    it.place(0, topBarHeight + tabBarHeight)
                }

                topBarPlaceables.forEach {
                    it.place(0, 0)
                }

                tabBarPlaceables.forEach {
                    it.place(0, topBarHeight)
                }

                snackBarPlaceables.forEach {
                    it.place(0, layoutHeight - snackBarHeight)
                }
            }
        }
    }
}

internal enum class AppScaffoldContent {
    TOP_BAR,
    TAB_BAR,
    SNACK_BAR,
    CONTENT
}
