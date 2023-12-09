package com.donatas.dprofile.features.github

import com.donatas.dprofile.alerts.popup.PopUp
import com.donatas.dprofile.alerts.popup.PopUpController
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.loader.state.ListState
import com.donatas.dprofile.loader.state.RefreshState
import com.donatas.dprofile.paginator.DefaultPaginator
import com.donatas.dprofile.paginator.PerPage
import com.donatas.dprofile.paginator.state.PaginatorState
import com.donatas.dprofile.viewmodel.ViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GithubViewModel(
    private val userLogin: String,
    private val getUser: GetUser,
    private val popUpController: PopUpController,
    private val getRepositories: GetRepositories,
    private val delegate: GithubDelegate,
) : ViewModel() {
    private val paginator = DefaultPaginator<Repository>(perPage = PerPage(30), onLoad = { page, perPage ->
        getRepositories(
            page = page.value, perPage = perPage.value, login = userLogin
        )
    })

    private val _listState: MutableStateFlow<GithubListState> = MutableStateFlow(GithubListState.Loading)
    val listState: StateFlow<GithubListState> = _listState.asStateFlow()

    private val _refreshState: MutableStateFlow<GithubRefreshState> = MutableStateFlow(GithubRefreshState.Idle)
    val refreshState: StateFlow<GithubRefreshState> = _refreshState.asStateFlow()
    val paginatorState: StateFlow<PaginatorState> get() = paginator.state
    val endReached: StateFlow<Boolean> get() = paginator.endReached

    private val _scrollToTop: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val scrollToTop: StateFlow<Boolean> get() = _scrollToTop.asStateFlow()

    val popUp: StateFlow<PopUp?> = popUpController.popUp

    private val _user: MutableStateFlow<UserState> = MutableStateFlow(UserState.Loading)
    val user: StateFlow<UserState> = _user.asStateFlow()

    init {
        loadInitially()
    }

    private fun loadInitially() = scope.launch {
        _listState.value = GithubListState.Loading
        awaitAll(
            async {
                paginator.init()
            },
            async {
                getUser(userLogin).onSuccess {
                    _user.value = UserState.Data(user = it)
                }.onFailure {
                    _user.value = UserState.Error(title = "Unable to load Github profile, try again")
                }
            }
        )

        handleListStateAfterLoading()
    }

    private fun handleListStateAfterLoading() {
        val state = paginator.listState.value

        if (state is ListState.Error || _user.value is UserState.Error && _refreshState.value is GithubRefreshState.Idle) {
            _listState.value = GithubListState.Error(
                title = "Unable to load data, try again!"
            )

            return
        }

        if (state is ListState.Empty) {
            _listState.value = GithubListState.Empty(
                title = "Currently, I do not have any Github repositories."
            )

            return
        }

        if (state is ListState.Data && paginator.refreshState.value is RefreshState.Idle) {
            _scrollToTop.value = state.isFirstPage
            _listState.value = GithubListState.Data(
                data = state.data,
                total = state.total,
                isFirstPage = state.isFirstPage
            )
        }
    }

    private fun handleListStateAfterPagination() {
        val listState = paginator.listState.value
        if (paginator.state.value is PaginatorState.Idle && listState is ListState.Data) {
            _listState.value = GithubListState.Data(
                data = listState.data,
                total = listState.total,
                isFirstPage = listState.isFirstPage
            )
        }
    }

    private fun showRefreshErrorPopUp() {
        scope.launch {
            popUpController.show(PopUp {
                title = "Unable to refresh some of the data! Please try again."
                onClick = {
                    popUpController.dismiss()
                }
            })
        }
    }

    fun onScrollToTopDone() {
        _scrollToTop.value = false
    }

    fun onLoadNextPage() = scope.launch {
        paginator.loadNextItems()
        handleListStateAfterPagination()
    }

    fun onRefresh() {
        var userRefreshSuccessful: Boolean = false

        scope.launch {
            _refreshState.value = GithubRefreshState.Refreshing
            awaitAll(
                async {
                    paginator.refresh()
                },
                async {
                    getUser(userLogin).onSuccess {
                        _user.value = UserState.Data(user = it)
                        userRefreshSuccessful = true
                    }.onFailure {
                        userRefreshSuccessful = false
                    }
                }
            )

            handleListStateAfterLoading()
            _refreshState.value = GithubRefreshState.Idle

            if ((paginator.refreshState.value is RefreshState.Error || !userRefreshSuccessful) && paginator.listState.value !is ListState.Empty) {
                showRefreshErrorPopUp()
            }
        }
    }

    fun onRetryLoading() = loadInitially()

    fun onRetryNextPage() = scope.launch {
        paginator.retryNextPage()
        handleListStateAfterPagination()
    }

    fun onSearch() = delegate.onSearch()
    fun onDetails(repository: Repository) = delegate.onUrl(url = repository.htmlUrl)
    fun onVisitProfile() {
        val user = _user.value as? UserState.Data ?: return

        delegate.onUrl(url = user.user.htmlUrl)
    }
}
