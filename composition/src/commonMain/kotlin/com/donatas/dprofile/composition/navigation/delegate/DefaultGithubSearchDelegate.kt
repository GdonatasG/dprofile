package com.donatas.dprofile.composition.navigation.delegate

import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.composition.navigation.flow.FilterFlow
import com.donatas.dprofile.features.github.search.presentation.GithubSearchDelegate

class DefaultGithubSearchDelegate(
    private val navigator: Navigator,
    private val filterFlow: FilterFlow
) : GithubSearchDelegate {
    override fun onBack() {
        navigator.pop()
    }

    override fun onFilter() {
        filterFlow.start()
    }

    override fun onDetails(repoUrl: String) {
        navigator.openBrowser(repoUrl)
    }
}
