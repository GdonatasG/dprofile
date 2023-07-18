package com.donatas.dprofile.features.aboutme.roadtoprogramming

import com.donatas.dprofile.feature.Screen
import org.koin.core.component.KoinComponent

actual class RoadToProgrammingScreen actual constructor() : Screen, KoinComponent {
    private val factory = Factory()
    override fun controller(): NSViewController {
        throw NotImplementedError()
    }
}
