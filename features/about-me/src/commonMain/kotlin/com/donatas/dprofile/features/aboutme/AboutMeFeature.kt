package com.donatas.dprofile.features.aboutme

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class AboutMeFeature(): KoinComponent {
    init {
        loadModules()
    }

    fun screen(): AboutMeScreen = get<AboutMeScreen>()
}
