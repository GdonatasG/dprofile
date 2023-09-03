package com.donatas.dprofile.features.github

import com.donatas.dprofile.alerts.popup.DefaultPopUpController
import com.donatas.dprofile.features.github.presentation.GetUser
import com.donatas.dprofile.features.github.presentation.GithubDelegate
import com.donatas.dprofile.features.github.presentation.GithubViewModel
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.paginator.DefaultPaginator
import com.donatas.dprofile.paginator.Paginator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    viewModel<GithubViewModel>() {
        GithubViewModel(
            githubUserLogin = get<GithubUserLogin>().value,
            paginator = get<Paginator<Repository>>(qualifier = paginatorQualifier),
            getUser = get<GetUser>(),
            popUpController = DefaultPopUpController(),
            delegate = get<GithubDelegate>()
        )
    }
}
