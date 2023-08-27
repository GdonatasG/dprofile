package com.donatas.dprofile.githubservices.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUserResponse(
    @SerialName("login")
    val login: String,
    @SerialName("location")
    val location: String? = null,
    @SerialName("followers")
    val followers: Int,
    @SerialName("following")
    val following: Int
)
