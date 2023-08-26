package com.donatas.dprofile.compose.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DPullRefreshIndicator(
    modifier: Modifier = Modifier,
    refreshing: Boolean,
    state: PullRefreshState
) {
    PullRefreshIndicator(
        modifier = modifier,
        refreshing = refreshing,
        state = state,
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary
    )
}
