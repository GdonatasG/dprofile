package com.donatas.dprofile.githubservices.user

import com.donatas.dprofile.githubservices.common.http.GithubHttpClient
import com.donatas.dprofile.githubservices.common.http.request

interface UserService {
    suspend fun getUser(user: String): Result<GithubUserResponse>
}

class DefaultUserService(private val client: GithubHttpClient) : UserService {
    override suspend fun getUser(user: String): Result<GithubUserResponse> = client.request(GetUser(user))
}
