package com.donatas.dprofile.composition.di

import com.donatas.dprofile.githubservices.common.http.GithubHttpClient
import com.donatas.dprofile.githubservices.repository.DefaultRepositoryService
import com.donatas.dprofile.githubservices.repository.RepositoryService
import com.donatas.dprofile.githubservices.user.DefaultUserService
import com.donatas.dprofile.githubservices.user.UserService
import org.koin.core.module.Module
import org.koin.dsl.module

internal val githubServicesModule: Module = module {
    single<RepositoryService> {
        DefaultRepositoryService(
            client = get<GithubHttpClient>()
        )
    }

    single<UserService> {
        DefaultUserService(
            client = get<GithubHttpClient>()
        )
    }
}
