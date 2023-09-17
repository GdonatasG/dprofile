package com.donatas.dprofile.features.github

data class GithubUser(
    val login: String,
    val location: String?,
    val followers: Int,
    val following: Int,
    val avatarUrl: String?,
    val htmlUrl: String
)
