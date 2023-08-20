package com.donatas.dprofile.loader

import com.donatas.dprofile.loader.state.ListState
import com.donatas.dprofile.loader.state.RefreshState
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultLoader<Item : Any>(
    private val onLoad: suspend () -> LoadingResult<Item>
) : Loader<Item>() {
    private var isMakingRequest: Boolean = false

    // region STATE
    override val _listState: MutableStateFlow<ListState<Item>> = MutableStateFlow(ListState.Loading)
    override val _refreshState: MutableStateFlow<RefreshState> = MutableStateFlow(RefreshState.Idle)
    // endregion

    override suspend fun init() {
        if (isMakingRequest) return

        isMakingRequest = true
        _listState.value = ListState.Loading

        val result = onLoad()

        isMakingRequest = false
        _refreshState.value = RefreshState.Idle

        result.on(error = {
            _listState.value = ListState.Error(title = this.title, message = this.message)
        })
    }

    override suspend fun refresh() {
        if (isMakingRequest) return

        isMakingRequest = true
        _refreshState.value = RefreshState.Refreshing

        val result = onLoad()

        isMakingRequest = false
        _refreshState.value = RefreshState.Idle

        result.on(error = {
            _refreshState.value = RefreshState.Error(title = this.title, message = this.message)
        })
    }

    override suspend fun retry() {
        if (_listState.value is ListState.Error) {
            return init()
        }

        if (_refreshState.value is RefreshState.Error) {
            return refresh()
        }
    }

    override fun reset() {
        isMakingRequest = false
        _listState.value = ListState.Loading
        _refreshState.value = RefreshState.Idle
    }

    private fun LoadingResult<Item>.on(
        data: LoadingResult.Data<Item>.() -> Unit = {},
        empty: LoadingResult.Empty.() -> Unit = {},
        error: LoadingResult.Error.() -> Unit = {}
    ) {
        when (this) {
            is LoadingResult.Data -> {
                _listState.value = ListState.Data(
                    data = this.data, total = this.total, isFirstPage = true
                )
                data()
            }

            is LoadingResult.Empty -> {
                _listState.value = ListState.Empty(title = this.title, message = this.message)
                empty()
            }

            is LoadingResult.Error -> error()
        }

    }

}
