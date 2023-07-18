package com.donatas.dprofile.features.github.search.presentation

import com.donatas.dprofile.viewmodel.ViewModel

class GithubSearchViewModel(
    private val delegate: GithubSearchDelegate
) : ViewModel() {
    fun onBack() = delegate.onBack()
    fun onFilter() = delegate.onFilter()
    fun onDetails() = delegate.onDetails()
}
