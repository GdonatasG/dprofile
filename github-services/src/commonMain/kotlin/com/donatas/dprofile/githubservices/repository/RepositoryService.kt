package com.donatas.dprofile.githubservices.repository

import com.donatas.dprofile.githubservices.common.http.GithubHttpClient
import com.donatas.dprofile.githubservices.common.http.request

interface RepositoryService {
    suspend fun getRepositories(builder: GetRepositories.() -> Unit): Result<RepositoriesDataResponse>
}

class DefaultRepositoryService(private val client: GithubHttpClient) :
    RepositoryService {
    override suspend fun getRepositories(builder: GetRepositories.() -> Unit): Result<RepositoriesDataResponse> =
        client.request(GetRepositories().apply(builder))
}
