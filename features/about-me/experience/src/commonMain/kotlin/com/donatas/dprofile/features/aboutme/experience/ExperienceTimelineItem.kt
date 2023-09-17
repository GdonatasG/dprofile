package com.donatas.dprofile.features.aboutme.experience

data class ExperienceTimelineItem(
    val title: String,
    val subtitle: String,
    val description: String,
    val location: String,
    var animated: Boolean = false
)
