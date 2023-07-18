package com.donatas.dprofile.features.github

import com.donatas.dprofile.features.github.presentation.GithubDelegate
import com.donatas.dprofile.features.github.presentation.GithubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    viewModel<GithubViewModel>() {
        GithubViewModel(
            delegate = get<GithubDelegate>()
        )
    }
}
