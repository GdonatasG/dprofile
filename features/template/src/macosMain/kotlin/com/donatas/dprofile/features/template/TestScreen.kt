package com.donatas.dprofile.features.template

import com.donatas.dprofile.feature.Screen
import org.koin.core.component.KoinComponent

actual class TestScreen actual constructor() : Screen, KoinComponent {
    private val factory = Factory()
    override fun controller(): NSViewController {
        throw NotImplementedError()
    }
}
