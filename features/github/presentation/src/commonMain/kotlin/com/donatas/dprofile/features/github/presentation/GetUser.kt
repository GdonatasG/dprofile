package com.donatas.dprofile.features.github.presentation

interface GetUser {
    suspend operator fun invoke(user: String): Result<GithubUser>
}
