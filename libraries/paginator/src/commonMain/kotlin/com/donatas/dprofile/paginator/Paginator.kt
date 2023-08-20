package com.donatas.dprofile.paginator

import com.donatas.dprofile.loader.Loader
import com.donatas.dprofile.paginator.state.PaginatorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class Paginator<Item : Any> : Loader<Item>() {
    protected abstract val _state: MutableStateFlow<PaginatorState>
    val state: StateFlow<PaginatorState>
        get() = _state.asStateFlow()

    protected abstract val _endReached: MutableStateFlow<Boolean>

    val endReached: StateFlow<Boolean>
        get() = _endReached.asStateFlow()

    abstract suspend fun loadNextItems()
}
