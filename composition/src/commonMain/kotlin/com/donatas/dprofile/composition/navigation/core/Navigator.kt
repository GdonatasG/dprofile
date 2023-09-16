package com.donatas.dprofile.composition.navigation.core

import com.donatas.dprofile.alerts.Alert
import com.donatas.dprofile.feature.Modal
import com.donatas.dprofile.feature.Screen

interface Navigator {
    fun set(screen: Screen)
    fun push(screen: Screen)
    fun replace(screen: Screen, previous: Int = 1)
    fun push(modal: Modal)
    fun show(alert: Alert)
    fun openBrowser(url: String)
    fun pop()
    fun closeModal()
}
