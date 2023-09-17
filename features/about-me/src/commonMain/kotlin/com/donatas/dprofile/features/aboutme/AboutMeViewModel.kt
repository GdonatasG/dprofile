package com.donatas.dprofile.features.aboutme

import com.donatas.dprofile.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class Tab(val route: String) {
    EXPERIENCE("experience"),
    EDUCATION("education"),
    SKILLS("skills"),
    ROAD_TO_PROGRAMMING("road_to_programming")
}

class AboutMeViewModel : ViewModel() {
    private val _selectedTab: MutableStateFlow<Tab> = MutableStateFlow(Tab.EXPERIENCE)
    val selectedTab: StateFlow<Tab> get() = _selectedTab.asStateFlow()

    fun setTab(tab: Tab) {
        if (_selectedTab.value == tab) return

        _selectedTab.value = tab
    }
}
