package com.donatas.dprofile.composition.navigation.flow

import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.features.filter.FilterModal

class FilterFlow(
    private val navigator: Navigator,
    private val filterModal: FilterModal
) {
    fun start() {
        navigator.push(filterModal)
    }
}
