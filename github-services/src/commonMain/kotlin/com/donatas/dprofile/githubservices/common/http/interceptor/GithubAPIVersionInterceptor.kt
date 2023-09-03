package com.donatas.dprofile.githubservices.common.http.interceptor

import com.donatas.dprofile.githubservices.common.http.GithubHttpClient
import com.donatas.dprofile.githubservices.common.request.GithubRequest
import com.donatas.dprofile.http.models.HttpRequest
import com.donatas.dprofile.http.models.Token
import kotlin.reflect.KClass

class GithubAPIVersionInterceptor(
    private val client: GithubHttpClient, private val version: GithubAPIVersion
) : GithubHttpClient {
    override suspend fun <T : Any> request(request: GithubRequest, clazz: KClass<T>): Result<T> {

        return client.request(GithubAPIVersionInterceptorWrapper(request = request, version = version), clazz)
    }

    private class GithubAPIVersionInterceptorWrapper(
        private val request: GithubRequest, private val version: GithubAPIVersion
    ) : GithubRequest {
        override fun build(): HttpRequest {
            val httpRequest = request.build()

            return HttpRequest(
                method = httpRequest.method,
                endpoint = httpRequest.endpoint,
                body = httpRequest.body,
                params = httpRequest.params,
                headers = mapOf("X-GitHub-Api-Version" to version.value) + httpRequest.headers
            )
        }
    }
}

fun GithubHttpClient.specifyAPIVersion(version: GithubAPIVersion): GithubHttpClient =
    GithubAPIVersionInterceptor(client = this, version = version)

enum class GithubAPIVersion(val value: String) {
    NOVEMBER_28_2022("2022-11-28")
}


