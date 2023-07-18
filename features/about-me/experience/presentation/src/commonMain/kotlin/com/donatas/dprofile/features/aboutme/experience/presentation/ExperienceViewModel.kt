package com.donatas.dprofile.features.aboutme.experience.presentation

import com.donatas.dprofile.viewmodel.ViewModel

enum class Tab(val route: String) {
    EXPERIENCE("experience"),
    EDUCATION("education"),
    SKILLS("skills"),
    ROAD_TO_PROGRAMMING("road_to_programming")
}

class ExperienceViewModel: ViewModel() {
}
