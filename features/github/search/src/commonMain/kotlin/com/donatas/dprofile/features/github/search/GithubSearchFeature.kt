package com.donatas.dprofile.features.github.search

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class GithubSearchFeature(): KoinComponent {
    init {
        loadModules()
    }

    fun screen(): GithubSearchScreen = get<GithubSearchScreen>()
}
