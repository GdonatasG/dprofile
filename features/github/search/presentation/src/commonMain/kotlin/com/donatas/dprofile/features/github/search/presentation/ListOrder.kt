package com.donatas.dprofile.features.github.search.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListOrder {
    private var _value: MutableStateFlow<Type> = MutableStateFlow(Type.DESC)
    val value: StateFlow<Type> = _value.asStateFlow()

    fun change() {
        _value.value = if (_value.value == Type.DESC) {
            Type.ASC
        } else Type.DESC
    }

    enum class Type {
        DESC, ASC
    }
}
