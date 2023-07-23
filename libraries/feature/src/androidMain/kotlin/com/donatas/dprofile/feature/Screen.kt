package com.donatas.dprofile.feature

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable

class Components(
    val lazyListState: LazyListState? = null
) {
    companion object {
        fun empty(): Components = Components()
    }
}

actual interface Screen {
    @Composable
    fun Compose(components: Components)
}
