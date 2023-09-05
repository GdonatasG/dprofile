package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.composition.navigation.delegate.DefaultContactsDelegate
import com.donatas.dprofile.composition.navigation.delegate.DefaultGithubDelegate
import com.donatas.dprofile.composition.navigation.delegate.DefaultGithubSearchDelegate
import com.donatas.dprofile.composition.navigation.flow.FilterFlow
import com.donatas.dprofile.composition.navigation.flow.GithubSearchFlow
import com.donatas.dprofile.features.contacts.presentation.ContactsDelegate
import com.donatas.dprofile.features.github.presentation.GithubDelegate
import com.donatas.dprofile.features.github.search.presentation.GithubSearchDelegate
import org.koin.core.module.Module
import org.koin.dsl.module

internal val delegateModule: Module = module {
    single<GithubDelegate>() {
        DefaultGithubDelegate(
            navigator = get<Navigator>()
        )
    }

    single<GithubSearchDelegate>() {
        DefaultGithubSearchDelegate(
            navigator = get<Navigator>(), filterFlow = get<FilterFlow>()
        )
    }

    single<ContactsDelegate>() {
        DefaultContactsDelegate(
            navigator = get<Navigator>()
        )
    }
}
