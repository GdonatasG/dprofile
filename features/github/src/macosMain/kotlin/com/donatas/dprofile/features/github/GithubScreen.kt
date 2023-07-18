package com.donatas.dprofile.features.github

import com.donatas.dprofile.feature.Screen
import org.koin.core.component.KoinComponent

actual class GithubScreen actual constructor() : Screen, KoinComponent {
    private val factory = Factory()
    override fun controller(): NSViewController {
        throw NotImplementedError()
    }
}
