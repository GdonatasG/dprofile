package com.donatas.dprofile.composition.presentation

import com.donatas.dprofile.feature.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class BottomTab(
    val type: Type,
    val factory: () -> Screen
) {
    enum class Type {
        ABOUT_ME,
        GITHUB,
        CONTACTS
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        other as BottomTab
        return other.type == type
    }
}

class BottomTabBarController(
    val tabs: List<BottomTab>,
    private val onFinished: () -> Unit
) {
    private val _selectedTab: MutableStateFlow<BottomTab> = MutableStateFlow(tabs.first())
    val selectedTab = _selectedTab.asStateFlow()

    fun select(tab: BottomTab) {
        if (!tabs.contains(tab)) return
        if (_selectedTab.value == tab) return
        _selectedTab.value = tab
    }

    fun onFinish() {
        onFinished()
    }
}
