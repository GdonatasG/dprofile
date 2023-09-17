package com.donatas.dprofile.features.github

sealed class GithubRefreshState {
    object Idle : GithubRefreshState()
    object Refreshing : GithubRefreshState()
}
