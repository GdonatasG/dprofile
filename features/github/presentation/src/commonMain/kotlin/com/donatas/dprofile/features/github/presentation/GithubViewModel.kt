package com.donatas.dprofile.features.github.presentation

import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.viewmodel.ViewModel

class GithubViewModel(
    private val delegate: GithubDelegate
) : ViewModel() {
    val repositories: List<Repository> = List<Repository>(20) { index ->
        Repository(
            title = "Repository${index + 1}",
            language = "Language${index + 1}",
            url = "https://www.google.com"
        )
    }

    fun onSearch() = delegate.onSearch()
    fun onDetails(repository: Repository) = delegate.onDetails(repoUrl = repository.url)
}
