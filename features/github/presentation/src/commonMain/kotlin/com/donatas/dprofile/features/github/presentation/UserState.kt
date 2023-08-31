package com.donatas.dprofile.features.github.presentation

sealed class UserState {
    object Loading : UserState()
    data class Error(val title: String) : UserState()
    data class Data(val user: GithubUser) : UserState()
}
