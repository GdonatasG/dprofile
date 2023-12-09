package com.donatas.dprofile.features.github.search

import com.donatas.dprofile.alerts.Alert
import com.donatas.dprofile.alerts.popup.PopUp
import com.donatas.dprofile.alerts.popup.PopUpController
import com.donatas.dprofile.features.filter.shared.observable.AppliedFilters
import com.donatas.dprofile.features.filter.shared.observable.AppliedFiltersObservable
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.loader.state.ListState
import com.donatas.dprofile.loader.state.RefreshState
import com.donatas.dprofile.paginator.DefaultPaginator
import com.donatas.dprofile.paginator.Paginator
import com.donatas.dprofile.paginator.PerPage
import com.donatas.dprofile.paginator.state.PaginatorState
import com.donatas.dprofile.utils.observer.Observer
import com.donatas.dprofile.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.scope.Scope

class GithubSearchViewModel(
    private val appliedFiltersObservable: AppliedFiltersObservable,
    private val getRepositories: GetRepositories,
    private val popUpController: PopUpController,
    private val delegate: GithubSearchDelegate,
    private val alert: Alert.Coordinator
) : ViewModel() {
    private var requestQuery: String = ""

    private val paginator: Paginator<Repository> =
        DefaultPaginator<Repository>(perPage = PerPage(30), onLoad = { page, perPage ->
            getRepositories(
                page = page.value,
                perPage = perPage.value,
                globalSearch = _globalSearch.value,
                query = requestQuery,
                order = _order.value
            )
        })

    private val _viewState: MutableStateFlow<GithubSearchViewState> =
        MutableStateFlow(GithubSearchViewState.defaultIdle())
    val viewState: StateFlow<GithubSearchViewState> = _viewState.asStateFlow()

    val listState: StateFlow<ListState<Repository>> = paginator.listState
    val paginatorState: StateFlow<PaginatorState> = paginator.state
    val refreshState: StateFlow<RefreshState> = paginator.refreshState

    private val _scrollToTop: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val scrollToTop: StateFlow<Boolean> get() = _scrollToTop.asStateFlow()

    val endReached: StateFlow<Boolean> get() = paginator.endReached

    private var searchJob: Job? = null
    private val _searchField: MutableStateFlow<String> = MutableStateFlow("")
    val searchField: StateFlow<String> = _searchField.asStateFlow()

    private val _globalSearch: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val globalSearch: StateFlow<Boolean> = _globalSearch.asStateFlow()

    val popUp: StateFlow<PopUp?> = popUpController.popUp

    private val _order: MutableStateFlow<Order> = MutableStateFlow(Order.DESC)
    val order: StateFlow<Order> = _order.asStateFlow()

    private val _appliedFiltersState: MutableStateFlow<AppliedFiltersState> = MutableStateFlow(AppliedFiltersState.None)
    val appliedFiltersState: StateFlow<AppliedFiltersState> = _appliedFiltersState.asStateFlow()

    private val appliedFiltersObserver: Observer<AppliedFilters> = object : Observer<AppliedFilters> {
        override fun update(value: AppliedFilters) {
            if (value.updateRequired) {
                _appliedFiltersState.value =
                    if (value.filters.isNotEmpty()) AppliedFiltersState.Content(filterValues = value.filters) else AppliedFiltersState.None
                applyChanges()
            }
        }
    }

    private fun applyChanges() {
        val trimmedQuery = _searchField.value.trim()

        paginator.reset()
        searchJob?.cancel()
        searchJob = null

        if (trimmedQuery.isEmpty()) {
            requestQuery = ""
        }

        if (trimmedQuery.isEmpty() && _appliedFiltersState.value is AppliedFiltersState.None) {
            _viewState.value = GithubSearchViewState.defaultIdle()
            _order.value = Order.DESC

            return
        }

        searchJob = scope.launch {
            if (trimmedQuery.isNotEmpty() && trimmedQuery != requestQuery) {
                delay(500)
                requestQuery = trimmedQuery
            }

            if (_viewState.value !is GithubSearchViewState.Searched) {
                _viewState.value = GithubSearchViewState.Searched
            }
            paginator.init()
        }
    }

    init {
        scope.launch {
            paginator.listState.collect { state ->
                when (state) {
                    is ListState.Data -> {
                        _scrollToTop.value = state.isFirstPage
                    }

                    else -> {}
                }
            }
        }
        scope.launch {
            paginator.refreshState.collect { state ->
                when (state) {
                    is RefreshState.Error -> {
                        showRefreshErrorPopUp()
                    }

                    else -> {}
                }
            }
        }
    }

    override fun onAppear() {
        super.onAppear()
        appliedFiltersObservable.add(appliedFiltersObserver)
    }

    fun onSearch(query: String) {
        _searchField.value = query

        if (requestQuery == query.trim()) return

        applyChanges()
    }

    fun onGlobalSearchChanged(value: Boolean) {
        if (_globalSearch.value == value) return

        _globalSearch.value = value

        applyChanges()
    }

    fun onListOrderChanged() {
        _order.value = if (_order.value == Order.DESC) Order.ASC else Order.DESC
        applyChanges()
    }

    fun onDescribeGlobalSearch() {
        alert.show(Alert {
            title = "Global search"
            message =
                "When this functionality is enabled, you will see repositories fetched from all Github accounts,\n" + " otherwise - only that belong to me (GdonatasG)"
            buttons = listOf(
                Alert.Button.Cancel("OK")
            )
        })
    }

    fun onRetryLoading() = scope.launch {
        paginator.init()
    }

    fun onRefresh() {
        searchJob?.cancel()
        searchJob = null
        searchJob = scope.launch {
            paginator.refresh()
        }
    }

    fun onScrollToTopDone() {
        _scrollToTop.value = false
    }

    fun onLoadNextPage() = scope.launch {
        paginator.loadNextItems()
    }

    fun onRetryNextPage() = scope.launch {
        paginator.retryNextPage()
    }

    private fun showRefreshErrorPopUp() {
        scope.launch {
            popUpController.show(PopUp {
                title = "Unable to refresh repositories! Please try again."
                onClick = {
                    popUpController.dismiss()
                }
            })
        }
    }

    override fun onDisappear() {
        super.onDisappear()
        appliedFiltersObservable.remove(appliedFiltersObserver)
    }

    // region NAVIGATION
    fun onBack() = delegate.onBack()
    fun onFilter() = delegate.onFilter()
    fun onDetails(repository: Repository) = delegate.onDetails(repoUrl = repository.htmlUrl)
    // endregion
}
