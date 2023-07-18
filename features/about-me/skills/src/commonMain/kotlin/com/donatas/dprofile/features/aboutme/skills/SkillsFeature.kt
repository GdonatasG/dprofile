package com.donatas.dprofile.features.aboutme.skills

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class SkillsFeature(): KoinComponent {
    init {
        loadModules()
    }

    fun screen(): SkillsScreen = get<SkillsScreen>()
}
