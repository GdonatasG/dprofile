package com.donatas.dprofile.features.filter

import com.donatas.dprofile.features.filter.shared.observable.FilterStoreObservableCache
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class FilterFeature(): KoinComponent {
    fun modal(cache: FilterStoreObservableCache): FilterModal {
        loadModules(cache)
        return get<FilterModal>()
    }

}
