package com.donatas.dprofile.features.github.search

import com.donatas.dprofile.features.github.search.presentation.GithubSearchDelegate
import com.donatas.dprofile.features.github.search.presentation.GithubSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    viewModel<GithubSearchViewModel>() {
        GithubSearchViewModel(
            delegate = get<GithubSearchDelegate>()
        )
    }
}
