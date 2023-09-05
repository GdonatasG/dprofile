package com.donatas.dprofile.features.github.search.presentation

import com.donatas.dprofile.alerts.popup.PopUp
import com.donatas.dprofile.alerts.popup.PopUpController
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.loader.SearchQueryHolder
import com.donatas.dprofile.loader.state.ListState
import com.donatas.dprofile.loader.state.RefreshState
import com.donatas.dprofile.paginator.Paginator
import com.donatas.dprofile.paginator.state.PaginatorState
import com.donatas.dprofile.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class GithubSearchViewState {
    data class Idle(val message: String) : GithubSearchViewState()
    object Searched : GithubSearchViewState()

    companion object {
        fun defaultIdle(): Idle = Idle(message = "Search and/or filter repositories")
    }
}

class GithubSearchViewModel(
    private val searchQueryHolder: SearchQueryHolder,
    private val paginator: Paginator<Repository>,
    private val popUpController: PopUpController,
    private val delegate: GithubSearchDelegate
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

    val popUp: StateFlow<PopUp?> = popUpController.popUp

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

    fun onSearch(query: String) {
        if (query.trim().isEmpty()) {
            searchJob?.cancel()
            searchJob = null
            resetViewState()
            _searchField.value = query
            searchQueryHolder.setQuery(query)
            return
        }

        _searchField.value = query
        if (searchQueryHolder.query.value == query) return

        searchJob?.cancel()
        searchJob = null

        searchJob = scope.launch {
            delay(500)
            searchQueryHolder.setQuery(query)
            _viewState.value = GithubSearchViewState.Searched
            paginator.init()
        }
    }

    fun onRetryLoading() = scope.launch {
        paginator.init()
    }

    fun onRefresh() = scope.launch {
        paginator.refresh()
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

    private fun resetViewState() {
        _viewState.value = GithubSearchViewState.defaultIdle()
        paginator.reset()
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

    // region NAVIGATION
    fun onBack() = delegate.onBack()
    fun onFilter() = delegate.onFilter()
    fun onDetails(repository: Repository) = delegate.onDetails(repoUrl = repository.htmlUrl)
    // endregion
}
