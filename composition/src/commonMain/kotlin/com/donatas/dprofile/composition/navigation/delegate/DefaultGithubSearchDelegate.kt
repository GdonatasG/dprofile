package com.donatas.dprofile.composition.navigation.delegate

import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.features.github.search.presentation.GithubSearchDelegate

class DefaultGithubSearchDelegate(
    private val navigator: Navigator
) : GithubSearchDelegate {
    override fun onBack() {
        navigator.pop()
    }

    override fun onFilter() {
        TODO("Not yet implemented")
    }
}
