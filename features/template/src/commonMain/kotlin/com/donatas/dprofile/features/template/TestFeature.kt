package com.donatas.dprofile.features.template

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class TestFeature(): KoinComponent {
    init {
        loadModules()
    }

    fun screen(): TestScreen = get<TestScreen>()
}
