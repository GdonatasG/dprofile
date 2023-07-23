package com.donatas.dprofile.compose.components.appbar

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.donatas.dprofile.compose.components.extension.calculateScrollOffset

class AppBarHeader(
    val lazyListState: LazyListState, val headerHeight: Dp, val content: @Composable () -> Unit
) {
    @Composable
    fun Compose() {
        AppBarHeaderLayout(
            lazyListState = lazyListState, headerHeight = headerHeight
        ) {
            content()
        }
    }
}

@Composable
fun AppBarHeaderLayout(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    headerHeight: Dp,
    content: @Composable () -> Unit,
) {
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }

    Box(modifier = modifier
        .height(headerHeight)
        .graphicsLayer {
            val scrollOffset = lazyListState.calculateScrollOffset(additionalHeightInPx = headerHeightPx)
            translationY = -scrollOffset.toFloat() / 2f // Parallax effect
            alpha = (-1f / headerHeightPx) * scrollOffset + 1
        }
        /* .graphicsLayer {
             translationY = -lazyListState.value.toFloat() / 2f // Parallax effect
             alpha = (-1f / headerHeightPx) * lazyListState.value + 1
         }*/) {
        content()
    }
}
