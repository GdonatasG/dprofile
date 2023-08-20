package com.donatas.dprofile.paginator

import com.donatas.dprofile.loader.LoadingResult
import com.donatas.dprofile.loader.state.ListState
import com.donatas.dprofile.loader.state.RefreshState
import com.donatas.dprofile.paginator.state.PaginatorState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.job
import kotlin.coroutines.coroutineContext

open class DefaultPaginator<Item : Any>(
    private val initialPage: Page = Page(1),
    private val perPage: PerPage,
    private val onLoad: suspend (page: Page, perPage: PerPage) -> LoadingResult<Item>
) : Paginator<Item>() {
    private var currentPage: Page = initialPage
    private var isMakingRequest: Boolean = false
    private var initJob: Job? = null
    private var nextPageJob: Job? = null
    private var refreshJob: Job? = null

    // region STATE
    override val _state: MutableStateFlow<PaginatorState> = MutableStateFlow(PaginatorState.Idle)
    override val _listState: MutableStateFlow<ListState<Item>> = MutableStateFlow(ListState.Loading)
    override val _endReached: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val _refreshState: MutableStateFlow<RefreshState> = MutableStateFlow(RefreshState.Idle)
    // endregion

    override fun reset() {
        isMakingRequest = false
        currentPage = initialPage
        _state.value = PaginatorState.Idle
        _listState.value = ListState.Loading
        _endReached.value = false
        _refreshState.value = RefreshState.Idle
    }

    override suspend fun init() {
        initJob?.cancel()
        initJob = null
        initJob = coroutineContext.job

        refreshJob?.cancel()
        refreshJob = null

        nextPageJob?.cancel()
        nextPageJob = null

        currentPage = initialPage
        isMakingRequest = true
        _listState.value = ListState.Loading

        val result = onLoad(currentPage, perPage)
        isMakingRequest = false
        _refreshState.value = RefreshState.Idle

        return result.on(
            data = {
                updateList(this)
                currentPage = currentPage.next()
            },
            empty = {
                _listState.value = ListState.Empty(title = this.title, message = this.message)

                currentPage = currentPage.next()
                _endReached.value = true
            },
            error = {
                setError(this)
            }
        )
    }

    override suspend fun loadNextItems() {
        if (_state.value is PaginatorState.Loading) return
        if (_state.value is PaginatorState.Error) return

        _loadNextItems()
    }

    private suspend fun _loadNextItems() {
        if (_endReached.value) return
        if (isMakingRequest) return

        if (isFirstPage()) throw IllegalArgumentException(
            "loadNextItems() should be used only for pagination! \n" +
                    "Use init() function for initial loading"
        )

        isMakingRequest = true

        nextPageJob?.cancel()
        nextPageJob = null
        nextPageJob = coroutineContext.job

        _state.value = PaginatorState.Loading

        val result = onLoad(currentPage, perPage)

        nextPageJob = null
        result.on(
            data = {
                _state.value = PaginatorState.Idle
                updateList(this)
                currentPage = currentPage.next()
            },
            empty = {
                _state.value = PaginatorState.Idle
                currentPage = currentPage.next()
                _endReached.value = true
            },
            error = {
                setError(this)
            }
        )
        delay(1000)
        isMakingRequest = false
    }

    override suspend fun refresh() {
        if (_listState.value !is ListState.Data) return
        if (_refreshState.value is RefreshState.Refreshing) return
        nextPageJob?.cancel()
        nextPageJob = null

        refreshJob = coroutineContext.job

        isMakingRequest = true
        _refreshState.value = RefreshState.Refreshing

        val result = onLoad(initialPage, perPage)
        isMakingRequest = false
        _refreshState.value = RefreshState.Idle
        refreshJob = null

        return result.on(
            data = {
                currentPage = initialPage
                _state.value = PaginatorState.Idle
                updateList(this)
                currentPage = currentPage.next()
            },
            empty = {
                currentPage = initialPage
                _listState.value = ListState.Empty(
                    title = this.title,
                    message = this.message
                )
            },
            error = {
                _refreshState.value = RefreshState.Error(title = this.title, message = this.message)
            }
        )
    }

    override suspend fun retry() {
        if (_state.value is PaginatorState.Error) {
            return _loadNextItems()
        }

        if (_listState.value is ListState.Empty || _listState.value is ListState.Error) {
            return init()
        }
    }

    // region HELPERS
    private fun LoadingResult<Item>.on(
        data: LoadingResult.Data<Item>.() -> Unit = {},
        empty: LoadingResult.Empty.() -> Unit = {},
        error: LoadingResult.Error.() -> Unit = {}
    ) {
        when (this) {
            is LoadingResult.Data -> {
                _endReached.value = this.total <= perPage.value
                data()
            }

            is LoadingResult.Empty -> empty()
            is LoadingResult.Error -> error()
        }
    }

    private fun isFirstPage(): Boolean = currentPage.value == initialPage.value

    private fun setError(error: LoadingResult.Error) {
        if (isFirstPage()) {
            _state.value = PaginatorState.Idle
            _listState.value = ListState.Error(title = error.title, message = error.message)

            return
        }

        _state.value = PaginatorState.Error(title = error.title, message = error.message)
    }

    private fun updateList(data: LoadingResult.Data<Item>) {
        if (isFirstPage()) {
            _listState.value = ListState.Data(
                data = data.data, total = data.total, isFirstPage = isFirstPage()
            )

            return
        }

        val currentData: ListState.Data<Item> =
            _listState.value as? ListState.Data<Item> ?: throw IllegalStateException("Incorrect paginator list state!")

        _listState.value = ListState.Data(
            data = (currentData.data + data.data).toSet().toList(), total = data.total, isFirstPage = isFirstPage()
        )


    }


    // endregion
}
