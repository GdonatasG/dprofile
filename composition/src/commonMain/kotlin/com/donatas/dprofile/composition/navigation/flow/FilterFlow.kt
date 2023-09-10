package com.donatas.dprofile.composition.navigation.flow

import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.features.filter.FilterFeature
import com.donatas.dprofile.features.filter.FilterModal
import com.donatas.dprofile.features.filter.shared.observable.FilterStoreObservableCache

class FilterFlow(
    private val navigator: Navigator,
    private val filterFeature: FilterFeature
) {
    fun start(cache: FilterStoreObservableCache) {
        navigator.push(filterFeature.modal(cache))
    }
}
