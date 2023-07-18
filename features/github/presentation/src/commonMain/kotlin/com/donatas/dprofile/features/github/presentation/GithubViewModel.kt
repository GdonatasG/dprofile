package com.donatas.dprofile.features.github.presentation

import com.donatas.dprofile.viewmodel.ViewModel

class GithubViewModel(
    private val delegate: GithubDelegate
) : ViewModel() {
    fun onSearch() = delegate.onSearch()
    fun onDetails() = delegate.onDetails(repoUrl = "https://www.google.com")
}
