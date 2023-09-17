package com.donatas.dprofile.features.aboutme.experience

import com.donatas.dprofile.viewmodel.ViewModel

class ExperienceViewModel : ViewModel() {
    val timeline: List<ExperienceTimelineItem> = listOf(
        ExperienceTimelineItem(
            title = "Mobile applications developer",
            subtitle = "2022 - present",
            description = "Developing Android applications using Kotlin Multiplatform " +
                    "and Jetpack Compose",
            location = "Teltonika Networks"
        ),
        ExperienceTimelineItem(
            title = "Flutter internship",
            subtitle = "2022 (3 mos.)",
            description = "Practising mobile applications development using Flutter framework",
            location = "Visma Lithuania"
        )
    )
}
