package com.donatas.dprofile.composition.navigation

import com.donatas.dprofile.feature.Modal
import com.donatas.dprofile.feature.Screen

interface Navigator {
    fun set(screen: Screen)
    fun push(screen: Screen)
    fun push(modal: Modal)
    fun pop()
}
