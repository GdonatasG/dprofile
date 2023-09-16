package com.donatas.dprofile.features.github.search.presentation

sealed class GithubSearchViewState {
    data class Idle(val message: String) : GithubSearchViewState()
    object Searched : GithubSearchViewState()

    companion object {
        fun defaultIdle(): Idle = Idle(message = "Search and/or filter repositories")
    }
}
