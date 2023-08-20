package com.donatas.dprofile.githubservices.common.request

import com.donatas.dprofile.http.models.HttpRequest

interface GithubRequest {
    fun build(): HttpRequest
}
