package com.donatas.dprofile.features.github.search

import com.donatas.dprofile.features.github.search.presentation.GithubSearchDelegate
import com.donatas.dprofile.features.github.search.presentation.GithubSearchViewModel
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.loader.SearchQueryHolder
import com.donatas.dprofile.paginator.Paginator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    viewModel<GithubSearchViewModel>() {
        GithubSearchViewModel(
            searchQueryHolder = get<SearchQueryHolder>(qualifier = queryHolderQualifier),
            paginator = get<Paginator<Repository>>(qualifier = paginatorQualifier),
            delegate = get<GithubSearchDelegate>()
        )
    }
}
