package com.donatas.dprofile.features.github.search.presentation

import com.donatas.dprofile.features.filter.shared.FilterValue

sealed class AppliedFiltersState {
    object None : AppliedFiltersState()
    data class Content(val filterValues: List<FilterValue>) : AppliedFiltersState()
}
