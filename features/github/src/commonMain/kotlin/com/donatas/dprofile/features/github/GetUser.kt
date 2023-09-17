package com.donatas.dprofile.features.github

interface GetUser {
    suspend operator fun invoke(user: String): Result<GithubUser>
}
