package com.donatas.dprofile.githubservices.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoriesDataResponse(
    @SerialName("items") val items: List<RepositoryResponse>, @SerialName("total_count") val total: Int
)

@Serializable
data class RepositoryResponse(
    @SerialName("name") val name: String,
    @SerialName("language") val language: String?,
    @SerialName("html_url") val htmlUrl: String
)
