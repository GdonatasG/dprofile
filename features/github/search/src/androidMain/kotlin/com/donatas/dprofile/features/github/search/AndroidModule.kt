package com.donatas.dprofile.features.github.search

import com.donatas.dprofile.alerts.Alert
import com.donatas.dprofile.alerts.popup.DefaultPopUpController
import com.donatas.dprofile.features.github.search.presentation.GithubSearchDelegate
import com.donatas.dprofile.features.github.search.presentation.GithubSearchViewModel
import com.donatas.dprofile.features.github.search.presentation.GlobalSearchHandler
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.loader.SearchQueryHolder
import com.donatas.dprofile.paginator.Paginator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    viewModel<GithubSearchViewModel>() {
        GithubSearchViewModel(
            globalSearchHandler = get<GlobalSearchHandler>(qualifier = globalSearchHandlerQualifier),
            searchQueryHolder = get<SearchQueryHolder>(qualifier = queryHolderQualifier),
            paginator = get<Paginator<Repository>>(qualifier = paginatorQualifier),
            popUpController = DefaultPopUpController(),
            delegate = get<GithubSearchDelegate>(),
            alert = get<Alert.Coordinator>()
        )
    }
}
