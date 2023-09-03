package com.donatas.dprofile.githubservices.common.http.interceptor

import com.donatas.dprofile.githubservices.common.http.GithubHttpClient
import com.donatas.dprofile.githubservices.common.request.GithubRequest
import com.donatas.dprofile.http.models.HttpRequest
import com.donatas.dprofile.http.models.Token
import kotlin.reflect.KClass

class AuthorizationInterceptor(
    private val client: GithubHttpClient, private val tokenProvider: suspend () -> Token
) : GithubHttpClient {
    override suspend fun <T : Any> request(request: GithubRequest, clazz: KClass<T>): Result<T> {
        val token = tokenProvider()

        return client.request(AuthorizationInterceptorWrapper(request = request, token = token), clazz)
    }

    private class AuthorizationInterceptorWrapper(
        private val request: GithubRequest, private val token: Token
    ) : GithubRequest {
        override fun build(): HttpRequest {
            val httpRequest = request.build()

            return HttpRequest(
                method = httpRequest.method,
                endpoint = httpRequest.endpoint,
                body = httpRequest.body,
                params = httpRequest.params,
                headers = mapOf("Authorization" to token.get()) + httpRequest.headers
            )
        }

    }
}

fun GithubHttpClient.addAuthorizationHandler(tokenProvider: suspend () -> Token): GithubHttpClient =
    AuthorizationInterceptor(client = this, tokenProvider = tokenProvider)
