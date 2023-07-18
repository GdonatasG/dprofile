package com.donatas.dprofile.features.github

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class GithubFeature(): KoinComponent {
    init {
        loadModules()
    }

    fun screen(): GithubScreen = get<GithubScreen>()
}
