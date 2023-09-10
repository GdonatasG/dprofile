package com.donatas.dprofile.features.filter.shared

import com.donatas.dprofile.features.filter.shared.model.FilterModel
import com.donatas.dprofile.features.filter.shared.model.SingleChoicePredefinedFilterModel

data class FilterStore(
    val data: List<FilterModel>
) {
    fun getSelected(): List<FilterValue> = data.flatMap {
        it.list
    }.filter {
        it.selected && !it.neutral
    }.map {
        it.copy()
    }

    fun reset() {
        data.forEach {
            it.onReset()
        }
    }

    fun unselectFilter(filterValue: FilterValue) {
        data.firstOrNull { it.key == filterValue.parent.key }?.also { filterModel ->
            if (filterModel is SingleChoicePredefinedFilterModel) {
                filterModel.onReset()

                return@also
            }

            if (filterValue.selected) {
                filterModel.onToggle(filterValue)
            }
        }
    }

    fun get(key: String): FilterModel? = data.firstOrNull { it.key == key }

    companion object {
        fun empty(): FilterStore = FilterStore(data = emptyList())
    }

    fun deepCopy(): FilterStore {
        val newData = mutableListOf<FilterModel>()
        data.forEach {
            newData.add(it.copy())
        }
        return FilterStore(newData)
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || this::class != other::class) return false

        other as FilterStore

        return data == other.data
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }
}

fun FilterModel.filterSelected(): List<String>? = this.list.filter { it.selected && !it.neutral }
    .map { it.id }
    .toList()
    .takeIf { it.isNotEmpty() }
