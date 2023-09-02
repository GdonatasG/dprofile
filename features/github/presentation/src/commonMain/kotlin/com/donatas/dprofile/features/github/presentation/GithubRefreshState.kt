package com.donatas.dprofile.features.github.presentation

sealed class GithubRefreshState {
    object Idle : GithubRefreshState()
    object Refreshing : GithubRefreshState()
}
