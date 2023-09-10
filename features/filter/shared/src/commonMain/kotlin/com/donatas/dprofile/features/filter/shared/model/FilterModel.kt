package com.donatas.dprofile.features.filter.shared.model

import com.donatas.dprofile.features.filter.shared.FilterValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class FilterModel {
    abstract val key: String
    abstract var list: List<FilterValue>
    abstract val title: String

    protected abstract val _selected: MutableStateFlow<List<FilterValue>>
    val selected: StateFlow<List<FilterValue>> get() = _selected.asStateFlow()

    protected abstract val _temporarySelected: MutableStateFlow<List<FilterValue>>
    val temporarySelected: StateFlow<List<FilterValue>> get() = _temporarySelected.asStateFlow()

    abstract fun onApply()
    abstract fun onToggle(filter: FilterValue)

    protected abstract fun copy(list: List<FilterValue>): FilterModel
    fun copy(): FilterModel = copy(list.map { it.copy() })

    abstract fun onReset()

    override fun equals(other: Any?): Boolean {
        if (other == null || this::class != other::class) return false

        other as FilterModel

        if (key != other.key) return false
        if (title != other.title) return false
        if (list.size != other.list.size) return false
        list.forEachIndexed { index, item ->
            if (item.id != other.list[index].id) return false
            if (item.selected != other.list[index].selected) return false
        }

        return true
    }

    override fun hashCode(): Int {
        return key.hashCode()
    }
}
