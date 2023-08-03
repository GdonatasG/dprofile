package com.donatas.dprofile.features.aboutme.skills.presentation

data class Skill(
    val value: String,
    val level: Int,
    var animated: Boolean = false
) {
    val maxLevel: Int = 5
}
