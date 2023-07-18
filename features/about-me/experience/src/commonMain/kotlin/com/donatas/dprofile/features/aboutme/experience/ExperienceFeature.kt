package com.donatas.dprofile.features.aboutme.experience

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class ExperienceFeature(): KoinComponent {
    init {
        loadModules()
    }

    fun screen(): ExperienceScreen = get<ExperienceScreen>()
}
