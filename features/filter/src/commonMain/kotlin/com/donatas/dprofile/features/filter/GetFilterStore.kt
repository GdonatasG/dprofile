package com.donatas.dprofile.features.filter

import com.donatas.dprofile.features.filter.shared.FilterStore

interface GetFilterStore {
    operator fun invoke(): FilterStore
}
