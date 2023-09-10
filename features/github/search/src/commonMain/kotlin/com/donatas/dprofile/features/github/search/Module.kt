package com.donatas.dprofile.features.github.search

import com.donatas.dprofile.features.filter.shared.FilterStore
import com.donatas.dprofile.features.filter.shared.FilterValue
import com.donatas.dprofile.features.filter.shared.ParentData
import com.donatas.dprofile.features.filter.shared.model.SingleChoicePredefinedFilterModel
import com.donatas.dprofile.features.filter.shared.observable.FilterStoreObservableCache
import com.donatas.dprofile.features.github.search.presentation.GlobalSearchHandler
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.githubservices.repository.RepositoryResponse
import com.donatas.dprofile.githubservices.repository.RepositoryService
import com.donatas.dprofile.loader.LoadingResult
import com.donatas.dprofile.loader.SearchQueryHolder
import com.donatas.dprofile.paginator.DefaultPaginator
import com.donatas.dprofile.paginator.Paginator
import com.donatas.dprofile.paginator.PerPage
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal val paginatorQualifier: StringQualifier = named("search_repositories_paginator")
internal val queryHolderQualifier: StringQualifier = named("search_repositories_query_holder")
internal val globalSearchHandlerQualifier: StringQualifier = named("search_repositories_global_search_handler")

internal fun loadModules() = loadKoinModules(
    listOf(
        commonModule,
        platformModule
    )
)

internal val commonModule = module {
    single<GlobalSearchHandler>(qualifier = globalSearchHandlerQualifier) {
        GlobalSearchHandler(initiallySearchGlobally = false)
    }

    single<SearchQueryHolder>(qualifier = queryHolderQualifier) {
        SearchQueryHolder()
    }

    single<GetRepositories> {
        DefaultGetRepositoriesUseCase(
            globalSearchHandler = get<GlobalSearchHandler>(qualifier = globalSearchHandlerQualifier),
            searchQueryHolder = get<SearchQueryHolder>(qualifier = queryHolderQualifier),
            repositoryService = get<RepositoryService>()
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

    single<GithubSearchScreen> {
        GithubSearchScreen()
    }

    single<FilterStoreObservableCache> {
        FilterStoreObservableCache(
            initial = FilterStore(
                data = listOf(
                    SingleChoicePredefinedFilterModel(
                        key = "language",
                        title = "Language",
                        list = listOf(
                            FilterValue(
                                id = "all",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "All",
                                initialValue = true,
                                neutral = true
                            ),
                            FilterValue(
                                id = "java",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "Java"
                            ),
                            FilterValue(
                                id = "kotlin",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "Kotlin"
                            ),
                            FilterValue(
                                id = "dart",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "Dart"
                            ),
                            FilterValue(
                                id = "python",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "Python"
                            ),
                            FilterValue(
                                id = "javascript",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "Javascript"
                            ),
                            FilterValue(
                                id = "html",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "HTML"
                            )
                        )
                    ),
                    SingleChoicePredefinedFilterModel(
                        key = "sort",
                        title = "Sort",
                        list = listOf(
                            FilterValue(
                                id = "newest_to_oldest",
                                parent = ParentData(
                                    key = "sort",
                                    title = "Sort"
                                ),
                                name = "Newest to oldest",
                                initialValue = true
                            ),
                            FilterValue(
                                id = "oldest_to_newest",
                                parent = ParentData(
                                    key = "sort",
                                    title = "Sort"
                                ),
                                name = "Oldest to newest",
                            )
                        )
                    )
                )
            )
        )
    }
}

internal expect val platformModule: Module

internal class DefaultGetRepositoriesUseCase(
    private val globalSearchHandler: GlobalSearchHandler,
    private val searchQueryHolder: SearchQueryHolder,
    private val repositoryService: RepositoryService
) : GetRepositories {
    override suspend fun invoke(page: Int, perPage: Int): LoadingResult<Repository> {
        val query = searchQueryHolder.get()
        val searchGlobally = globalSearchHandler.value.value

        val result = repositoryService.getRepositories {
            this.page {
                this.page = page
                this.perPage = perPage
            }
            if (query.isNotEmpty()) {
                this.query(query)
            }
            if (!searchGlobally) {
                this.user("GdonatasG")
            }
        }

        return suspendCoroutine<LoadingResult<Repository>> { continuation ->
            result.onSuccess { success ->
                if (success.items.isEmpty()) {
                    continuation.resume(LoadingResult.Empty(title = "No results found"))

                    return@onSuccess
                }

                continuation.resume(
                    LoadingResult.Data(
                        data = success.items.map { it.toDomain() },
                        total = success.total
                    )
                )
            }.onFailure {
                println(it.printStackTrace())
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
