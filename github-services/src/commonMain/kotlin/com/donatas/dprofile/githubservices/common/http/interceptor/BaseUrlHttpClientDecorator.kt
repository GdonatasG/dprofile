package com.donatas.dprofile.githubservices.common.http.interceptor

import com.donatas.dprofile.githubservices.common.http.GithubHttpClient
import com.donatas.dprofile.githubservices.common.request.GithubRequest
import com.donatas.dprofile.http.models.HttpRequest
import kotlin.reflect.KClass

class BaseUrlHttpClientDecorator(
    private val client: GithubHttpClient, private val baseUrl: String
) : GithubHttpClient {
    override suspend fun <T : Any> request(request: GithubRequest, clazz: KClass<T>): Result<T> {
        return client.request(Request(request, baseUrl), clazz)
    }

    private class Request(
        private val request: GithubRequest, private val baseUrl: String
    ) : GithubRequest {
        val httpRequest = request.build()

        override fun build(): HttpRequest = HttpRequest(
            method = httpRequest.method,
            endpoint = baseUrl + httpRequest.endpoint,
            body = httpRequest.body,
            params = httpRequest.params,
            headers = httpRequest.headers
        )

    }
}

fun GithubHttpClient.baseUrl(url: String): GithubHttpClient = BaseUrlHttpClientDecorator(this, url)
