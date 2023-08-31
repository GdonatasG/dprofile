package com.donatas.dprofile.features.github

import com.donatas.dprofile.features.github.presentation.GetUser
import com.donatas.dprofile.features.github.presentation.GithubUser
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.githubservices.repository.RepositoryResponse
import com.donatas.dprofile.githubservices.repository.RepositoryService
import com.donatas.dprofile.githubservices.user.UserService
import com.donatas.dprofile.loader.LoadingResult
import com.donatas.dprofile.loader.state.ListState
import com.donatas.dprofile.paginator.DefaultPaginator
import com.donatas.dprofile.paginator.Paginator
import com.donatas.dprofile.paginator.PerPage
import kotlinx.coroutines.delay
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal val paginatorQualifier: StringQualifier = named("repositories_paginator")

internal class GithubUserLogin(val value: String)

internal fun loadModules() = loadKoinModules(
    listOf(
        commonModule,
        platformModule
    )
)

internal val commonModule = module {
    single<GithubScreen> {
        GithubScreen()
    }

    single<GithubUserLogin> { GithubUserLogin(value = "GdonatasG") }

    single<GetRepositories> {
        DefaultGetRepositoriesUseCase(
            githubUserLogin = get<GithubUserLogin>(),
            repositoryService = get<RepositoryService>()
        )
    }

    single<GetUser> {
        DefaultGetUserUseCase(
            userService = get<UserService>()
        )
    }

    single<Paginator<Repository>>(qualifier = paginatorQualifier) {
        val getRepositories: GetRepositories = get<GetRepositories>()

        DefaultPaginator<Repository>(
            perPage = PerPage(30),
            onLoad = { page, perPage ->
                getRepositories(
                    page = page.value,
                    perPage = perPage.value
                )
            }
        )
    }
}

internal expect val platformModule: Module

internal class DefaultGetRepositoriesUseCase(
    private val githubUserLogin: GithubUserLogin,
    private val repositoryService: RepositoryService
) : GetRepositories {
    override suspend fun invoke(page: Int, perPage: Int): LoadingResult<Repository> {
        val result = repositoryService.getRepositories {
            this.page {
                this.page = page
                this.perPage = perPage
            }
            /*  this.language(com.donatas.dprofile.githubservices.repository.GetRepositories.Language.JAVA)*/
            this.user(githubUserLogin.value)
        }

        return suspendCoroutine<LoadingResult<Repository>> { continuation ->
            result.onSuccess { success ->
                if (success.items.isEmpty()) {
                    continuation.resume(LoadingResult.Empty(title = "No results found"))
                }

                continuation.resume(
                    LoadingResult.Data(
                        data = success.items.map { it.toDomain() },
                        total = success.total
                    )
                )
            }.onFailure {
                continuation.resume(
                    LoadingResult.Error(
                        title = "Failed!",
                        message = "Unable to load repositories, try again"
                    )
                )
            }
        }
    }
}

private fun RepositoryResponse.toDomain(): Repository = Repository(
    title = this.name,
    language = this.language,
    htmlUrl = this.htmlUrl
)


internal interface GetRepositories {
    suspend operator fun invoke(page: Int, perPage: Int): LoadingResult<Repository>
}

internal class DefaultGetUserUseCase(
    private val userService: UserService
) : GetUser {
    override suspend fun invoke(user: String): Result<GithubUser> {

        return userService.getUser(user).map {
            GithubUser(
                login = it.login,
                location = it.location,
                followers = it.followers,
                following = it.following
            )
        }
    }

}
