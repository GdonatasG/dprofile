package com.donatas.dprofile.composition.navigation.delegate

import com.donatas.dprofile.composition.navigation.core.Navigator
import com.donatas.dprofile.composition.navigation.flow.FilterFlow
import com.donatas.dprofile.features.filter.shared.observable.FilterStoreObservableCache
import com.donatas.dprofile.features.github.search.GithubSearchDelegate
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.scope.Scope

class DefaultGithubSearchDelegate(
    private val scope: Scope,
    private val navigator: Navigator,
    private val cache: FilterStoreObservableCache
) : GithubSearchDelegate, KoinComponent {
    private var popped: Boolean = false

    override fun onBack() {
        if (popped) return
        popped = true

        navigator.pop()
        scope.close()
    }

    override fun onFilter() {
        get<FilterFlow>().start(cache)
    }

    override fun onDetails(repoUrl: String) {
        navigator.openBrowser(repoUrl)
    }
}
