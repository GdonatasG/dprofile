package com.donatas.dprofile.features.filter.presentation

import com.donatas.dprofile.features.filter.shared.FilterStore

interface GetFilterStore {
    operator fun invoke(): FilterStore
}
