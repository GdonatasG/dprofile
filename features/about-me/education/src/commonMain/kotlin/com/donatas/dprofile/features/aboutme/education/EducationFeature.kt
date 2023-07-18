package com.donatas.dprofile.features.aboutme.education

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class EducationFeature(): KoinComponent {
    init {
        loadModules()
    }

    fun screen(): EducationScreen = get<EducationScreen>()
}
