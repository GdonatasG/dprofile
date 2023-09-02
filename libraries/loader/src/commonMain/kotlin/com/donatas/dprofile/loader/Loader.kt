package com.donatas.dprofile.loader

import com.donatas.dprofile.loader.state.ListState
import com.donatas.dprofile.loader.state.RefreshState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class Loader<Item : Any> {
    protected abstract val _listState: MutableStateFlow<ListState<Item>>
    val listState: StateFlow<ListState<Item>>
        get() = _listState.asStateFlow()

    protected abstract val _refreshState: MutableStateFlow<RefreshState>
    val refreshState: StateFlow<RefreshState>
        get() = _refreshState.asStateFlow()

    abstract suspend fun init()
    abstract suspend fun refresh()

    abstract fun reset()
}
