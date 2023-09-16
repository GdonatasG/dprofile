package com.donatas.dprofile.features.github.search

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class GithubSearchFeature : KoinComponent {

    fun screen(): GithubSearchScreen {
        loadModules()
        return get<GithubSearchScreen>()
    }
}
