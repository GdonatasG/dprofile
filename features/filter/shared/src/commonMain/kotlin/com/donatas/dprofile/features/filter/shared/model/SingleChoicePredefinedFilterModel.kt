package com.donatas.dprofile.features.filter.shared.model

import com.donatas.dprofile.features.filter.shared.FilterValue
import kotlinx.coroutines.flow.MutableStateFlow

data class SingleChoicePredefinedFilterModel(
    override val key: String,
    override val title: String,
    override var list: List<FilterValue>,
) : FilterModel() {
    override val _selected: MutableStateFlow<List<FilterValue>> = MutableStateFlow(list.filter { it.selected })

    override val _temporarySelected: MutableStateFlow<List<FilterValue>> = MutableStateFlow(emptyList())

    init {
        requireNotNull(list.firstOrNull { it.selected }) {
            "At least 1 value must be selected initially"
        }
        require(list.count { it.neutral } <= 1) {
            "No more than 1 value could be neutral"
        }
    }

    override fun onToggle(filter: FilterValue) {
        if (filter.selected) return

        filter.onToggle()

        _selected.value = emptyList()
        _selected.value = listOf(filter)
        list.map { listFilter ->
            listFilter.selected = ((listFilter.id == filter.id) && filter.selected)
        }
    }

    override fun onApply() {

    }

    override fun onReset() {
        val neutralFilter = list.firstOrNull { it.neutral }

        if (neutralFilter != null) {
            onToggle(neutralFilter)
            return
        }

        list.forEach {
            it.reset()
        }
    }

    override fun copy(list: List<FilterValue>): FilterModel {
        return SingleChoicePredefinedFilterModel(
            key = key, title = title, list = list
        )
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
