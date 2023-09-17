package com.donatas.dprofile.features.github

import com.donatas.dprofile.features.github.shared.Repository

sealed class GithubListState {
    data class Data(
        val data: List<Repository>,
        val total: Int,
        val isFirstPage: Boolean
    ) : GithubListState()

    data class Empty(val title: String, val message: String? = null) : GithubListState()

    object Loading : GithubListState()

    data class Error(val title: String, val message: String? = null) : GithubListState()
}
