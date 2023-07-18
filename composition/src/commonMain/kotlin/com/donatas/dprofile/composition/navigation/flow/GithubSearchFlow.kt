package com.donatas.dprofile.composition.navigation.flow

import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.features.github.search.GithubSearchScreen

class GithubSearchFlow(
    private val navigator: Navigator,
    private val githubSearchScreen: GithubSearchScreen
) {
    fun start() {
        navigator.push(githubSearchScreen)
    }

}
