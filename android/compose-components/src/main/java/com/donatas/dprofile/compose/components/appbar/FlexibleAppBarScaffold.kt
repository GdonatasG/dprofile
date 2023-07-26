package com.donatas.dprofile.compose.components.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.extension.calculateScrollOffset

@Composable
fun FlexibleAppBarScaffold(
    listState: LazyListState,
    title: String = "",
    centerTitle: Boolean = true,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    color: Color = MaterialTheme.colorScheme.background,
    flexibleSpace: @Composable () -> Unit,
    flexibleSpaceHeight: Dp,
    tabBar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding(),
        color = MaterialTheme.colorScheme.background,
        contentColor = contentColorFor(MaterialTheme.colorScheme.background)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Header(
                listState = listState,
                flexibleSpaceHeight = flexibleSpaceHeight,
            ) {
                flexibleSpace()
            }
            Body(
                listState = listState,
                flexibleSpaceHeight = flexibleSpaceHeight,
                tabBarHeightPx = 0
            ) {
                content()
            }
            AppBar(
                listState = listState,
                flexibleSpaceHeight = flexibleSpaceHeight,
                title = title,
                centerTitle = centerTitle,
                navigationIcon = navigationIcon,
                actions = actions,
                color = color
            )
        }
    }

}

@Composable
private fun Header(
    listState: LazyListState, flexibleSpaceHeight: Dp, content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val flexibleSpaceHeightPx = with(density) { flexibleSpaceHeight.toPx() }
    Box(modifier = Modifier
        .height(flexibleSpaceHeight)
        .graphicsLayer {
            val scrollOffset = listState.calculateScrollOffset(flexibleSpaceHeightPx)
            translationY = -scrollOffset // Parallax effect
            alpha = (-1f / flexibleSpaceHeightPx) * scrollOffset + 1
        }) {
        content()
    }
}

@Composable
private fun Body(
    listState: LazyListState,
    flexibleSpaceHeight: Dp,
    tabBarHeightPx: Int,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current

    val tabBarHeight = with(density) { tabBarHeightPx.toDp() }

    LazyColumn(
        state = listState
    ) {
        item {
            Spacer(Modifier.height(flexibleSpaceHeight + tabBarHeight))
        }
        item {
            content()
        }
    }
}

@Composable
private fun AppBar(
    listState: LazyListState,
    flexibleSpaceHeight: Dp,
    title: String = "",
    centerTitle: Boolean = true,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    color: Color = MaterialTheme.colorScheme.background,
) {
    val density = LocalDensity.current
    val flexibleSpaceBarHeightPx = with(density) { flexibleSpaceHeight.toPx() }
    val appBarHeightPx = with(density) { 64.dp.toPx().toInt() }

    val toolbarBottom by remember {
        mutableFloatStateOf(flexibleSpaceBarHeightPx - appBarHeightPx)
    }

    val showToolbar by remember {
        derivedStateOf {
            val scrollOffset = listState.calculateScrollOffset(flexibleSpaceBarHeightPx)
            scrollOffset >= toolbarBottom
        }
    }

    if (showToolbar) {
        DAppBar(
            title = title, centerTitle = centerTitle, navigationIcon = navigationIcon, actions = actions, color = color
        )
    }
}
