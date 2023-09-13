package com.donatas.dprofile.features.github.search.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GlobalSearchHandler(initiallySearchGlobally: Boolean) {
    private var _value: MutableStateFlow<Boolean> = MutableStateFlow(initiallySearchGlobally)
    val value: StateFlow<Boolean> = _value.asStateFlow()

    fun change(value: Boolean) {
        _value.value = value
    }
}
