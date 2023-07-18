package com.donatas.dprofile.features.filter

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class FilterFeature(): KoinComponent {
    init {
        loadModules()
    }

    fun modal(): FilterModal = get<FilterModal>()
}
