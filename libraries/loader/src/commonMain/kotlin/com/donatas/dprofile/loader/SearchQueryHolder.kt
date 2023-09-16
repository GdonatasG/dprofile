package com.donatas.dprofile.loader

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchQueryHolder {
    private var _query: MutableStateFlow<String> = MutableStateFlow("")
    val query: StateFlow<String> get() = _query.asStateFlow()

    fun get(): String = _query.value

    fun setQuery(query: String) {
        _query.value = query
    }

    fun reset() {
        _query.value = ""
    }
}
