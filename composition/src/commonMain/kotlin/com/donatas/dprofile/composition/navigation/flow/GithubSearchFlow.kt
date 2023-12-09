package com.donatas.dprofile.composition.navigation.flow

import com.donatas.dprofile.alerts.Alert
import com.donatas.dprofile.alerts.popup.DefaultPopUpController
import com.donatas.dprofile.composition.di.Scopes
import com.donatas.dprofile.composition.di.qualifier.AppliedFiltersObservableQualifier
import com.donatas.dprofile.composition.di.qualifier.FilterStoreObservableCacheQualifier
import com.donatas.dprofile.composition.extensions.getOrCreateScope
import com.donatas.dprofile.composition.extensions.sharedViewModel
import com.donatas.dprofile.composition.navigation.core.Navigator
import com.donatas.dprofile.composition.navigation.delegate.DefaultGithubSearchDelegate
import com.donatas.dprofile.composition.navigation.screens.FiltersModal
import com.donatas.dprofile.composition.navigation.screens.FiltersModalFactory
import com.donatas.dprofile.composition.navigation.screens.GithubSearchScreen
import com.donatas.dprofile.composition.navigation.screens.GithubSearchScreenFactory
import com.donatas.dprofile.features.filter.AddFilterStoreObserver
import com.donatas.dprofile.features.filter.ApplyFilters
import com.donatas.dprofile.features.filter.FiltersDelegate
import com.donatas.dprofile.features.filter.FiltersViewModel
import com.donatas.dprofile.features.filter.GetFilterStore
import com.donatas.dprofile.features.filter.RemoveFilterStoreObserver
import com.donatas.dprofile.features.filter.shared.FilterStore
import com.donatas.dprofile.features.filter.shared.FilterValue
import com.donatas.dprofile.features.filter.shared.ParentData
import com.donatas.dprofile.features.filter.shared.filterSelected
import com.donatas.dprofile.features.filter.shared.model.SingleChoicePredefinedFilterModel
import com.donatas.dprofile.features.filter.shared.observable.AppliedFiltersObservable
import com.donatas.dprofile.features.filter.shared.observable.FilterStoreObservableCache
import com.donatas.dprofile.features.github.search.GetRepositories
import com.donatas.dprofile.features.github.search.GithubSearchDelegate
import com.donatas.dprofile.features.github.search.GithubSearchViewModel
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.githubservices.common.Order
import com.donatas.dprofile.githubservices.repository.RepositoryResponse
import com.donatas.dprofile.githubservices.repository.RepositoryService
import com.donatas.dprofile.loader.LoadingResult
import com.donatas.dprofile.loader.SearchQueryHolder
import com.donatas.dprofile.paginator.DefaultPaginator
import com.donatas.dprofile.paginator.Paginator
import com.donatas.dprofile.paginator.PerPage
import com.donatas.dprofile.utils.observer.Observer
import org.koin.core.component.KoinScopeComponent
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GithubSearchFlow(
    private val navigator: Navigator
) : KoinScopeComponent {
    override val scope: Scope by lazy {
        getOrCreateScope<GithubSearchFlow>(scope = Scopes.GITHUB_SEARCH)
    }

    fun start() {
        val screen = scope.get<GithubSearchScreen>()

        navigator.push(screen)
    }

    companion object {
        val module = scope
    }
}

private val scope = module {
    scope<GithubSearchFlow> {
        scoped<GithubSearchScreen> {
            GithubSearchScreen(
                scope = this, factory = get<GithubSearchScreenFactory>()
            )
        }

        scoped<GithubSearchDelegate>() {
            DefaultGithubSearchDelegate(
                scope = this,
                navigator = get<Navigator>()
            )
        }

        scoped<GetRepositories> {
            DefaultGetRepositoriesUseCase(
                filterStoreObservableCache = get<FilterStoreObservableCache>(
                    qualifier = named(
                        FilterStoreObservableCacheQualifier.GITHUB_SEARCH
                    )
                ),
                repositoryService = get<RepositoryService>()
            )
        }

        scoped<FilterStoreObservableCache>(qualifier = named(FilterStoreObservableCacheQualifier.GITHUB_SEARCH)) {
            FilterStoreObservableCache(
                initial = FilterStore(
                    data = listOf(
                        SingleChoicePredefinedFilterModel(
                            key = "language", title = "Language", list = listOf(
                                FilterValue(
                                    id = "all",
                                    parent = ParentData(key = "language", title = "Language"),
                                    name = "All",
                                    initialValue = true,
                                    neutral = true
                                ), FilterValue(
                                    id = "java",
                                    parent = ParentData(key = "language", title = "Language"),
                                    name = "Java"
                                ), FilterValue(
                                    id = "kotlin",
                                    parent = ParentData(key = "language", title = "Language"),
                                    name = "Kotlin"
                                ), FilterValue(
                                    id = "dart",
                                    parent = ParentData(key = "language", title = "Language"),
                                    name = "Dart"
                                ), FilterValue(
                                    id = "python",
                                    parent = ParentData(key = "language", title = "Language"),
                                    name = "Python"
                                ), FilterValue(
                                    id = "javascript",
                                    parent = ParentData(key = "language", title = "Language"),
                                    name = "Javascript"
                                ), FilterValue(
                                    id = "html",
                                    parent = ParentData(key = "language", title = "Language"),
                                    name = "HTML"
                                ), FilterValue(
                                    id = "css", parent = ParentData(key = "language", title = "Language"), name = "CSS"
                                ), FilterValue(
                                    id = "shell",
                                    parent = ParentData(key = "language", title = "Language"),
                                    name = "SHELL"
                                ), FilterValue(
                                    id = "typescript",
                                    parent = ParentData(key = "language", title = "Language"),
                                    name = "TypeScript"
                                ), FilterValue(
                                    id = "c_plus_plus",
                                    parent = ParentData(key = "language", title = "Language"),
                                    name = "C++"
                                ), FilterValue(
                                    id = "c", parent = ParentData(key = "language", title = "Language"), name = "C"
                                ), FilterValue(
                                    id = "go", parent = ParentData(key = "language", title = "Language"), name = "GO"
                                ), FilterValue(
                                    id = "php", parent = ParentData(key = "language", title = "Language"), name = "PHP"
                                ), FilterValue(
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

        scoped<AppliedFiltersObservable>(qualifier = named(AppliedFiltersObservableQualifier.GITHUB_SEARCH)) {
            val cache: FilterStoreObservableCache =
                get<FilterStoreObservableCache>(qualifier = named(FilterStoreObservableCacheQualifier.GITHUB_SEARCH))
            AppliedFiltersObservable(
                initial = cache.get(), filterStoreObservable = cache
            )
        }

        sharedViewModel {
            GithubSearchViewModel(
                appliedFiltersObservable = get<AppliedFiltersObservable>(
                    qualifier = named(
                        AppliedFiltersObservableQualifier.GITHUB_SEARCH
                    )
                ),
                getRepositories = get<GetRepositories>(),
                popUpController = DefaultPopUpController(),
                delegate = get<GithubSearchDelegate>(),
                alert = get<Alert.Coordinator>(),
            )
        }

        factory<FiltersModal>() {
            FiltersModal(
                factory = get<FiltersModalFactory>(),
                viewModel = get<FiltersViewModel>()
            )
        }

        scoped<FiltersDelegate>(qualifier = named(Scopes.GITHUB_SEARCH)) {
            object : FiltersDelegate {
                override fun onBack() {
                    get<Navigator>().closeModal()
                }

            }
        }

        scoped<AddFilterStoreObserver>(qualifier = named(Scopes.GITHUB_SEARCH)) {
            DefaultAddFilterStoreObserver(
                cache = get<FilterStoreObservableCache>(qualifier = named(FilterStoreObservableCacheQualifier.GITHUB_SEARCH))
            )
        }

        scoped<RemoveFilterStoreObserver>(qualifier = named(Scopes.GITHUB_SEARCH)) {
            DefaultRemoveFilterStoreObserver(
                cache = get<FilterStoreObservableCache>(qualifier = named(FilterStoreObservableCacheQualifier.GITHUB_SEARCH))
            )
        }

        scoped<GetFilterStore>(qualifier = named(Scopes.GITHUB_SEARCH)) {
            DefaultGetFilterStore(
                cache = get<FilterStoreObservableCache>(qualifier = named(FilterStoreObservableCacheQualifier.GITHUB_SEARCH))
            )
        }

        scoped<ApplyFilters>(qualifier = named(Scopes.GITHUB_SEARCH)) {
            DefaultApplyFilters(
                cache = get<FilterStoreObservableCache>(qualifier = named(FilterStoreObservableCacheQualifier.GITHUB_SEARCH))
            )
        }

        sharedViewModel {
            FiltersViewModel(
                delegate = get<FiltersDelegate>(qualifier = named(Scopes.GITHUB_SEARCH)),
                addFilterStoreObserver = get<AddFilterStoreObserver>(qualifier = named(Scopes.GITHUB_SEARCH)),
                removeFilterStoreObserver = get<RemoveFilterStoreObserver>(qualifier = named(Scopes.GITHUB_SEARCH)),
                getFilterStore = get<GetFilterStore>(qualifier = named(Scopes.GITHUB_SEARCH)),
                applyFilters = get<ApplyFilters>(qualifier = named(Scopes.GITHUB_SEARCH))
            )
        }
    }
}

private class DefaultAddFilterStoreObserver(
    private val cache: FilterStoreObservableCache
) : AddFilterStoreObserver {
    override fun invoke(observer: Observer<FilterStore>) {
        cache.add(observer)
    }
}

private class DefaultRemoveFilterStoreObserver(
    private val cache: FilterStoreObservableCache
) : RemoveFilterStoreObserver {
    override fun invoke(observer: Observer<FilterStore>) {
        cache.remove(observer)
    }
}

private class DefaultGetFilterStore(
    private val cache: FilterStoreObservableCache
) : GetFilterStore {
    override fun invoke(): FilterStore = cache.get()
}

private class DefaultApplyFilters(
    private val cache: FilterStoreObservableCache
) : ApplyFilters {
    override fun invoke(filterStore: FilterStore) {
        cache.save(filterStore)
    }

}

internal class DefaultGetRepositoriesUseCase(
    private val filterStoreObservableCache: FilterStoreObservableCache,
    private val repositoryService: RepositoryService
) : GetRepositories {
    override suspend fun invoke(
        page: Int,
        perPage: Int,
        globalSearch: Boolean,
        query: String,
        order: com.donatas.dprofile.features.github.search.Order
    ): LoadingResult<Repository> {
        val filters = filterStoreObservableCache.get()

        val result = repositoryService.getRepositories {
            this.page {
                this.page = page
                this.perPage = perPage
            }
            if (query.isNotEmpty()) {
                this.query(query)
            }
            if (!globalSearch) {
                this.user("GdonatasG")
            }
            filters.get("language")?.filterSelected()?.firstOrNull()?.let {
                val selectedLanguage: com.donatas.dprofile.githubservices.repository.GetRepositories.Language? =
                    when (it) {
                        "java" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.JAVA
                        "kotlin" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.KOTLIN
                        "dart" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.DART
                        "python" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.PYTHON
                        "javascript" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.JAVASCRIPT
                        "html" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.HTML
                        "css" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.CSS
                        "shell" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.SHELL
                        "typescript" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.TYPESCRIPT
                        "c_plus_plus" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.C_PLUS_PLUS
                        "c" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.C
                        "go" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.GO
                        "php" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.PHP
                        "ruby" -> com.donatas.dprofile.githubservices.repository.GetRepositories.Language.RUBY
                        else -> null
                    }

                selectedLanguage?.let { language ->
                    this.language(language)
                }
            }
            this.order(
                order = if (order == com.donatas.dprofile.features.github.search.Order.DESC) Order.DESC else Order.ASC,
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
                        data = success.items.map { it.toDomain() }, total = success.total
                    )
                )
            }.onFailure {
                continuation.resume(
                    LoadingResult.Error(
                        title = "Failed!", message = "Unable to load repositories, try again"
                    )
                )
            }
        }
    }
}

private fun RepositoryResponse.toDomain(): Repository = Repository(
    title = this.name, language = this.language, htmlUrl = this.htmlUrl
)
