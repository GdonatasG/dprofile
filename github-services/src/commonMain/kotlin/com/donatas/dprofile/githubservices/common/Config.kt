package com.donatas.dprofile.githubservices.common

object Config {
    private const val DEFAULT_DNS = "api.github.com"
    val BASE_URL = "https://$DEFAULT_DNS"
    val SEARCH_ENDPOINT = "/search"
    val USERS_ENDPOINT = "/users"
}
