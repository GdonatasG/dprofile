package com.donatas.dprofile.githubservices.user

import com.donatas.dprofile.githubservices.common.Config
import com.donatas.dprofile.githubservices.common.request.GithubRequest
import com.donatas.dprofile.http.models.HttpMethod
import com.donatas.dprofile.http.models.HttpRequest

class GetUser(private val user: String) : GithubRequest {
    override fun build(): HttpRequest = HttpRequest(
        method = HttpMethod.GET, endpoint = Config.USERS_ENDPOINT + "/${user}", headers = mapOf(
            "Accept" to "application/vnd.github+json"
        )
    )
}
