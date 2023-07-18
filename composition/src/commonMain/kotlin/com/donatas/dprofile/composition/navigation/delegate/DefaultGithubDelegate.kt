package com.donatas.dprofile.composition.navigation.delegate

import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.composition.navigation.flow.GithubSearchFlow
import com.donatas.dprofile.features.github.presentation.GithubDelegate

class DefaultGithubDelegate(
    private val navigator: Navigator,
    private val githubSearchFlow: GithubSearchFlow
) : GithubDelegate {
    override fun onSearch() {
        githubSearchFlow.start()
    }

    override fun onDetails(repoUrl: String) {
        navigator.openBrowser(repoUrl)
    }
}
