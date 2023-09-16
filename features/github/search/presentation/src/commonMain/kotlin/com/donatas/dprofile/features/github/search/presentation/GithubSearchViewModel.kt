package com.donatas.dprofile.features.github.search.presentation

import com.donatas.dprofile.alerts.Alert
import com.donatas.dprofile.alerts.popup.PopUp
import com.donatas.dprofile.alerts.popup.PopUpController
import com.donatas.dprofile.features.filter.shared.observable.AppliedFilters
import com.donatas.dprofile.features.filter.shared.observable.AppliedFiltersObservable
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.loader.SearchQueryHolder
import com.donatas.dprofile.loader.state.ListState
import com.donatas.dprofile.loader.state.RefreshState
import com.donatas.dprofile.paginator.Paginator
import com.donatas.dprofile.paginator.state.PaginatorState
import com.donatas.dprofile.utils.observer.Observer
import com.donatas.dprofile.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GithubSearchViewModel(
    private val globalSearchHandler: GlobalSearchHandler,
    private val searchQueryHolder: SearchQueryHolder,
    private val listOrder: ListOrder,
    private val appliedFiltersObservable: AppliedFiltersObservable,
    private val paginator: Paginator<Repository>,
    private val popUpController: PopUpController,
    private val delegate: GithubSearchDelegate,
    private val alert: Alert.Coordinator,
) : ViewModel() {
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

    val globalSearch: StateFlow<Boolean> = globalSearchHandler.value

    val popUp: StateFlow<PopUp?> = popUpController.popUp

    val orderType: StateFlow<ListOrder.Type> = listOrder.value

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
            searchQueryHolder.reset()
        }

        if (trimmedQuery.isEmpty() && _appliedFiltersState.value is AppliedFiltersState.None) {
            _viewState.value = GithubSearchViewState.defaultIdle()

            return
        }

        searchJob = scope.launch {
            if (trimmedQuery.isNotEmpty() && trimmedQuery != searchQueryHolder.get()) {
                delay(500)
                searchQueryHolder.setQuery(trimmedQuery)
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


        if (searchQueryHolder.query.value == query.trim()) return

        applyChanges()
    }

    fun onGlobalSearchChanged(value: Boolean) {
        if (globalSearchHandler.value.value == value) return

        globalSearchHandler.change(value)

        applyChanges()
    }

    fun onListOrderChanged() {
        listOrder.change()
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
