package com.donatas.dprofile.composition.navigation.delegate

import com.donatas.dprofile.composition.navigation.flow.GithubSearchFlow
import com.donatas.dprofile.features.github.presentation.GithubDelegate

class DefaultGithubDelegate(
    private val githubSearchFlow: GithubSearchFlow
) : GithubDelegate {
    override fun onSearch() {
        githubSearchFlow.start()
    }
}
