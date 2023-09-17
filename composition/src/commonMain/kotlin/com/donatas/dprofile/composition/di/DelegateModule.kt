package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.navigation.core.Navigator
import com.donatas.dprofile.composition.navigation.delegate.DefaultContactsDelegate
import com.donatas.dprofile.composition.navigation.delegate.DefaultGithubSearchDelegate
import com.donatas.dprofile.composition.navigation.flow.FilterFlow
import com.donatas.dprofile.features.contacts.ContactsDelegate
import com.donatas.dprofile.features.filter.presentation.FiltersDelegate
import com.donatas.dprofile.features.filter.shared.observable.FilterStoreObservableCache
import com.donatas.dprofile.features.github.search.presentation.GithubSearchDelegate
import org.koin.core.module.Module
import org.koin.dsl.module

internal val delegateModule: Module = module {
    factory<GithubSearchDelegate>() {
        DefaultGithubSearchDelegate(
            navigator = get<Navigator>(),
            cache = get<FilterStoreObservableCache>(),
            filterFlow = get<FilterFlow>()
        )
    }

    single<FiltersDelegate> {
        object : FiltersDelegate {
            override fun onBack() {
                get<Navigator>().pop()
            }

        }
    }
}
