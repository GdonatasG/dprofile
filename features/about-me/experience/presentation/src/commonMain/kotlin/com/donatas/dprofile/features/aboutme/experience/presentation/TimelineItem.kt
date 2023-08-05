package com.donatas.dprofile.features.aboutme.experience.presentation

data class TimelineItem(
    val title: String,
    val subtitle: String,
    val description: String,
    val location: String,
    var animated: Boolean = false
)
