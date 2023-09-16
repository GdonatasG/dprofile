package com.donatas.dprofile.features.filter.shared

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ParentData(
    val key: String,
    val title: String
)

data class FilterValue(
    val id: String,
    val parent: ParentData,
    val name: String,
    val description: String? = null,
    val initialValue: Boolean = false,
    val neutral: Boolean = false,
    private var _selected: Boolean = initialValue
) {
    private val _state: MutableStateFlow<FilterData> = MutableStateFlow(
        FilterData(id = id, name = name, description = description, selected = _selected)
    )
    val state: StateFlow<FilterData> = _state.asStateFlow()

    var selected: Boolean
        get() = _selected
        set(value) {
            _selected = value
            _state.update { it.copy(selected = _selected) }
        }

    internal fun onToggle() {
        _selected = !_state.value.selected
        _state.update { it.copy(selected = _selected) }
    }

    internal fun reset() {
        _selected = initialValue
        _state.update { it.copy(selected = initialValue) }
    }

    fun copy(): FilterValue =
        FilterValue(
            parent = parent,
            id = id,
            name = name,
            description = description,
            initialValue = initialValue,
            neutral = neutral,
            _selected = _selected
        )
}
