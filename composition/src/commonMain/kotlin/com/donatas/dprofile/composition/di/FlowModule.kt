package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.MainScreen
import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.composition.navigation.flow.FilterFlow
import com.donatas.dprofile.composition.navigation.flow.GithubSearchFlow
import com.donatas.dprofile.composition.navigation.flow.MainFlow
import com.donatas.dprofile.features.filter.FilterFeature
import com.donatas.dprofile.features.github.search.GithubSearchFeature
import org.koin.core.module.Module
import org.koin.dsl.module

internal val flowModule: Module = module {
    single<MainFlow> {
        MainFlow(
            navigator = get<Navigator>(),
            mainScreen = get<MainScreen>()
        )
    }

    single<GithubSearchFlow> {
        GithubSearchFlow(
            navigator = get<Navigator>(),
            githubSearchScreen = get<GithubSearchFeature>().screen()
        )
    }

    single<FilterFlow> {
        FilterFlow(
            navigator = get<Navigator>(),
            filterModal = get<FilterFeature>().modal()
        )
    }
}
