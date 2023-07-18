package com.donatas.dprofile.features.aboutme.experience

import com.donatas.dprofile.feature.Screen
import org.koin.core.component.KoinComponent

actual class ExperienceScreen actual constructor() : Screen, KoinComponent {
    private val factory = Factory()
    override fun controller(): NSViewController {
        throw NotImplementedError()
    }
}
