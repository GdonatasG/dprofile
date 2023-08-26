package com.donatas.dprofile.features.github.presentation

import com.donatas.dprofile.alerts.popup.PopUp
import com.donatas.dprofile.alerts.popup.PopUpController
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.loader.state.ListState
import com.donatas.dprofile.loader.state.RefreshState
import com.donatas.dprofile.paginator.Paginator
import com.donatas.dprofile.paginator.state.PaginatorState
import com.donatas.dprofile.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class GithubViewModel(
    private val paginator: Paginator<Repository>,
    private val popUpController: PopUpController,
    private val delegate: GithubDelegate,
) : ViewModel() {
    val listState: StateFlow<ListState<Repository>> get() = paginator.listState

    val refreshState: StateFlow<RefreshState> = paginator.refreshState
    val paginatorState: StateFlow<PaginatorState> get() = paginator.state
    val endReached: StateFlow<Boolean> get() = paginator.endReached

    private val _scrollToTop: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val scrollToTop: StateFlow<Boolean> get() = _scrollToTop.asStateFlow()

    val popUp: StateFlow<PopUp?> = popUpController.popUp

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
                    showErrorPopUp()
                }

                _scrollToTop.value = state is RefreshState.Idle
            }
        }
    }

    private fun showErrorPopUp() {
        scope.launch {
            popUpController.show(PopUp {
                title = "Unable to load repositories! Try again"
                onClick = {
                    popUpController.dismiss()
                }
            })
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
