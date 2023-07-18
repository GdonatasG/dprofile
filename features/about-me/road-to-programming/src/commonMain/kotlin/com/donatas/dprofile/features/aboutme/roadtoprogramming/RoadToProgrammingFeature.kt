package com.donatas.dprofile.features.aboutme.roadtoprogramming

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class RoadToProgrammingFeature(): KoinComponent {
    init {
        loadModules()
    }

    fun screen(): RoadToProgrammingScreen = get<RoadToProgrammingScreen>()
}
