package com.donatas.dprofile.githubservices.repository

import com.donatas.dprofile.githubservices.common.http.GithubHttpClient
import com.donatas.dprofile.githubservices.common.http.request

class RepositoryService(private val client: GithubHttpClient) {
    suspend fun getRepositories(builder: GetRepositories.() -> Unit): Result<RepositoriesDataResponse> =
        client.request(GetRepositories().apply(builder))
}
