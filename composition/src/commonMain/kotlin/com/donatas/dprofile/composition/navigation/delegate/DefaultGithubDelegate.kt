package com.donatas.dprofile.composition.navigation.delegate

import com.donatas.dprofile.composition.navigation.core.Navigator
import com.donatas.dprofile.composition.navigation.flow.GithubSearchFlow
import com.donatas.dprofile.features.github.presentation.GithubDelegate
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class DefaultGithubDelegate(
    private val navigator: Navigator
) : GithubDelegate, KoinComponent {
    override fun onSearch() {
        get<GithubSearchFlow>().start()
    }

    override fun onUrl(url: String) {
        navigator.openBrowser(url)
    }
}
