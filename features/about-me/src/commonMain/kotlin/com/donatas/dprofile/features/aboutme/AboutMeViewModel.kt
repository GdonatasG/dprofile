package com.donatas.dprofile.features.aboutme

import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AboutMeTab(
    val type: Type,
    val factory: () -> Screen
) {
    enum class Type {
        EXPERIENCE,
        EDUCATION,
        SKILLS,
        ROAD_TO_PROGRAMMING
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        other as AboutMeTab

        return other.type == type
    }
}

class AboutMeViewModel(
    val tabs: List<AboutMeTab>
) : ViewModel() {
    private val _selectedTab: MutableStateFlow<AboutMeTab> = MutableStateFlow(tabs.first())
    val selectedTab: StateFlow<AboutMeTab> = _selectedTab.asStateFlow()

    fun select(tab: AboutMeTab) {
        if (!tabs.contains(tab)) return
        if (_selectedTab.value == tab) return
        _selectedTab.value = tab
    }
}
