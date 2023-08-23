package com.donatas.dprofile.features.github.presentation

import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.loader.state.ListState
import com.donatas.dprofile.loader.state.RefreshState
import com.donatas.dprofile.paginator.Paginator
import com.donatas.dprofile.paginator.state.PaginatorState
import com.donatas.dprofile.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GithubViewModel(
    private val paginator: Paginator<Repository>, private val delegate: GithubDelegate
) : ViewModel() {
    val repositories: List<Repository> = List<Repository>(20) { index ->
        Repository(
            title = "Repository${index + 1}", language = "Language${index + 1}", htmlUrl = "https://www.google.com"
        )
    }

    val listState: StateFlow<ListState<Repository>> get() = paginator.listState
    val refreshState: StateFlow<RefreshState> get() = paginator.refreshState
    val paginatorState: StateFlow<PaginatorState> get() = paginator.state
    val endReached: StateFlow<Boolean> get() = paginator.endReached

    private val _scrollToTop: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val scrollToTop: StateFlow<Boolean> get() = _scrollToTop.asStateFlow()

    init {
        scope.launch {
            paginator.init()
        }
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
                if (state is RefreshState.Error) {
                    // TODO: show error
                }

                _scrollToTop.value = state is RefreshState.Idle
            }
        }
    }

    fun onScrollToTopDone() {
        _scrollToTop.value = false
    }

    fun onLoadNextPage() {
        scope.launch {
            paginator.loadNextItems()
        }
    }

    fun onRefresh() {
        scope.launch {
            paginator.refresh()
        }
    }

    fun onRetry() {
        scope.launch {
            paginator.retry()
        }
    }

    fun onSearch() = delegate.onSearch()
    fun onDetails(repository: Repository) = delegate.onDetails(repoUrl = repository.htmlUrl)
}
