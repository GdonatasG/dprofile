package com.donatas.dprofile.compose.components.extension

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.calculateScrollOffset(
    additionalHeightInPx: Float?
) = this.firstVisibleItemIndex * (additionalHeightInPx ?: 1f) + this.firstVisibleItemScrollOffset
