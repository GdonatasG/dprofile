package com.donatas.dprofile.features.github.search

import com.donatas.dprofile.features.filter.shared.FilterStore
import com.donatas.dprofile.features.filter.shared.FilterValue
import com.donatas.dprofile.features.filter.shared.ParentData
import com.donatas.dprofile.features.filter.shared.filterSelected
import com.donatas.dprofile.features.filter.shared.model.SingleChoicePredefinedFilterModel
import com.donatas.dprofile.features.filter.shared.observable.AppliedFiltersObservable
import com.donatas.dprofile.features.filter.shared.observable.FilterStoreObservableCache
import com.donatas.dprofile.features.github.search.presentation.GlobalSearchHandler
import com.donatas.dprofile.features.github.search.presentation.ListOrder
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.githubservices.common.Order
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
import com.donatas.dprofile.githubservices.repository.GetRepositories.Language

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

    single<ListOrder> {
        ListOrder()
    }

    single<GetRepositories> {
        DefaultGetRepositoriesUseCase(
            filterStoreObservableCache = get<FilterStoreObservableCache>(),
            globalSearchHandler = get<GlobalSearchHandler>(qualifier = globalSearchHandlerQualifier),
            searchQueryHolder = get<SearchQueryHolder>(qualifier = queryHolderQualifier),
            listOrder = get<ListOrder>(),
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
                            ),
                            FilterValue(
                                id = "css",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "CSS"
                            ),
                            FilterValue(
                                id = "shell",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "SHELL"
                            ),
                            FilterValue(
                                id = "typescript",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "TypeScript"
                            ),
                            FilterValue(
                                id = "c_plus_plus",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "C++"
                            ),
                            FilterValue(
                                id = "c",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "C"
                            ),
                            FilterValue(
                                id = "go",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "GO"
                            ),
                            FilterValue(
                                id = "php",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "PHP"
                            ),
                            FilterValue(
                                id = "ruby",
                                parent = ParentData(key = "language", title = "Language"),
                                name = "Ruby"
                            )
                        )
                    )
                )
            )
        )
    }

    single<AppliedFiltersObservable> {
        val cache: FilterStoreObservableCache = get<FilterStoreObservableCache>()
        AppliedFiltersObservable(
            initial = cache.get(),
            filterStoreObservable = cache
        )
    }
}

internal expect val platformModule: Module

internal class DefaultGetRepositoriesUseCase(
    private val filterStoreObservableCache: FilterStoreObservableCache,
    private val globalSearchHandler: GlobalSearchHandler,
    private val searchQueryHolder: SearchQueryHolder,
    private val listOrder: ListOrder,
    private val repositoryService: RepositoryService
) : GetRepositories {
    override suspend fun invoke(page: Int, perPage: Int): LoadingResult<Repository> {
        val query = searchQueryHolder.get()
        val searchGlobally = globalSearchHandler.value.value
        val filters = filterStoreObservableCache.get()

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
            filters.get("language")?.filterSelected()?.firstOrNull()?.let {
                println("selected: $it")
                val selectedLanguage: Language? = when (it) {
                    "java" -> Language.JAVA
                    "kotlin" -> Language.KOTLIN
                    "dart" -> Language.DART
                    "python" -> Language.PYTHON
                    "javascript" -> Language.JAVASCRIPT
                    "html" -> Language.HTML
                    "css" -> Language.CSS
                    "shell" -> Language.SHELL
                    "typescript" -> Language.TYPESCRIPT
                    "c_plus_plus" -> Language.C_PLUS_PLUS
                    "c" -> Language.C
                    "go" -> Language.GO
                    "php" -> Language.PHP
                    "ruby" -> Language.RUBY
                    else -> null
                }

                selectedLanguage?.let { language ->
                    this.language(language)
                }
            }
            this.order(
                order = if (listOrder.value.value == ListOrder.Type.DESC) Order.DESC else Order.ASC,
                byField = com.donatas.dprofile.githubservices.repository.GetRepositories.SortField.UPDATED
            )
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
