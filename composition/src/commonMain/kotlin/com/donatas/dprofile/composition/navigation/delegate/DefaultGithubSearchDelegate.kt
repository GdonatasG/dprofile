package com.donatas.dprofile.composition.navigation.delegate

import com.donatas.dprofile.composition.navigation.core.Navigator
import com.donatas.dprofile.composition.navigation.flow.FilterFlow
import com.donatas.dprofile.features.filter.shared.observable.FilterStoreObservableCache
import com.donatas.dprofile.features.github.search.presentation.GithubSearchDelegate

class DefaultGithubSearchDelegate(
    private val navigator: Navigator,
    private val cache: FilterStoreObservableCache,
    private val filterFlow: FilterFlow
) : GithubSearchDelegate {
    override fun onBack() {
        navigator.pop()
    }

    override fun onFilter() {
        filterFlow.start(cache)
    }

    override fun onDetails(repoUrl: String) {
        navigator.openBrowser(repoUrl)
    }
}
