package com.donatas.dprofile.features.github.presentation

data class GithubUser(
    val login: String,
    val location: String? = null,
    val followers: Int,
    val following: Int
)
