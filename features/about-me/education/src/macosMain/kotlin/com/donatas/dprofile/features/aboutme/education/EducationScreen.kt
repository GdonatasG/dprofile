package com.donatas.dprofile.features.aboutme.education

import com.donatas.dprofile.feature.Screen
import org.koin.core.component.KoinComponent

actual class EducationScreen actual constructor() : Screen, KoinComponent {
    private val factory = Factory()
    override fun controller(): NSViewController {
        throw NotImplementedError()
    }
}
