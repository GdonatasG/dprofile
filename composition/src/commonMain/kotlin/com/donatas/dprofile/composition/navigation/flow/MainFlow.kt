package com.donatas.dprofile.composition.navigation.flow

import com.donatas.dprofile.composition.MainScreen
import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.utils.isDebug
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class MainFlow(
    private val navigator: Navigator,
    private val mainScreen: MainScreen
) {
    init {
        if (isDebug) {
            Napier.base(DebugAntilog())
        }
    }

    fun start() {
        navigator.set(mainScreen)
    }
}
