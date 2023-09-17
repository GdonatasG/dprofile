package com.donatas.dprofile.features.filter

import com.donatas.dprofile.features.filter.shared.FilterStore
import com.donatas.dprofile.utils.observer.Observer

interface RemoveFilterStoreObserver {
    operator fun invoke(observer: Observer<FilterStore>)
}
