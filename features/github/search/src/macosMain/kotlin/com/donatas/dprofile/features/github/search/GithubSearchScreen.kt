package com.donatas.dprofile.features.github.search

import com.donatas.dprofile.feature.Screen
import org.koin.core.component.KoinComponent

actual class GithubSearchScreen actual constructor() : Screen, KoinComponent {
    private val factory = Factory()
    override fun controller(): NSViewController {
        throw NotImplementedError()
    }
}
