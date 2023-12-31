package com.donatas.dprofile.features.filter

import com.donatas.dprofile.features.filter.shared.FilterStore

interface ApplyFilters {
    operator fun invoke(filterStore: FilterStore)
}
