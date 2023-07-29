package com.donatas.dprofile.compose.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.extension.calculateScrollOffset

class TabBar(
    val height: Dp,
    val content: @Composable () -> Unit
)

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
    tabBar: TabBar? = null,
    content: @Composable () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
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
                tabBarHeight = tabBar?.height ?: 0.dp
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
            tabBar?.let {
                TabBar(
                    listState = listState,
                    flexibleSpaceHeight = flexibleSpaceHeight,
                    tabBar = it
                )
            }
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
    tabBarHeight: Dp,
    content: @Composable () -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
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
    val appBarHeightPx = with(density) { appBarHeight.toPx().toInt() }

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

@Composable
private fun TabBar(
    listState: LazyListState,
    flexibleSpaceHeight: Dp,
    tabBar: TabBar,
    color: Color = MaterialTheme.colorScheme.background
) {
    val density = LocalDensity.current
    val flexibleSpaceBarHeightPx = with(density) { flexibleSpaceHeight.toPx() }
    val appBarHeightPx = with(density) { appBarHeight.toPx().toInt() }

    val toolbarBottom by remember {
        mutableFloatStateOf(flexibleSpaceBarHeightPx - appBarHeightPx)
    }

    val showToolbar by remember {
        derivedStateOf {
            val scrollOffset = listState.calculateScrollOffset(flexibleSpaceBarHeightPx)
            scrollOffset >= toolbarBottom
        }
    }

    Column {
        Spacer(modifier = Modifier.height(if (showToolbar) appBarHeight else flexibleSpaceHeight))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(tabBar.height)
                .graphicsLayer {
                    if (showToolbar) return@graphicsLayer
                    val scrollOffset = listState.calculateScrollOffset(flexibleSpaceBarHeightPx)
                    translationY = -scrollOffset // Parallax effect
                }
        ) {
            Box(modifier = Modifier.background(color)) {
                tabBar.content()
            }
        }
    }
}

private val appBarHeight: Dp = 64.dp
