package com.donatas.dprofile.features.aboutme.skills

data class Skill(
    val value: String,
    val level: Int
) {
    val maxLevel: Int = 5
}
