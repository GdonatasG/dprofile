package com.donatas.dprofile.githubservices.common.http

import com.donatas.dprofile.githubservices.common.decodeFromString
import com.donatas.dprofile.githubservices.common.exception.DecodeFromStringException
import com.donatas.dprofile.githubservices.common.request.GithubRequest
import com.donatas.dprofile.http.HttpClient
import com.donatas.dprofile.http.exceptions.ConnectTimeoutException
import com.donatas.dprofile.http.exceptions.UnresolvedAddressException
import com.donatas.dprofile.http.exceptions.UrlEncodingException
import kotlin.reflect.KClass

interface GithubHttpClient {
    suspend fun <T : Any> request(request: GithubRequest, clazz: KClass<T>): Result<T>
}

class DefaultGithubHttpClient(private val client: HttpClient) : GithubHttpClient {
    override suspend fun <T : Any> request(request: GithubRequest, clazz: KClass<T>): Result<T> {
        try {
            val response = client.request(request.build())

            if (response.isSuccessful) {
                return Result.success(decodeFromString(response.body, clazz))
            }

            return Result.failure(Exception())
        } catch (exception: UrlEncodingException) {
            return Result.failure(exception)
        } catch (exception: UnresolvedAddressException) {
            return Result.failure(exception)
        } catch (exception: ConnectTimeoutException) {
            return Result.failure(exception)
        } catch (exception: DecodeFromStringException) {
            return Result.failure(exception)
        }
    }
}

suspend inline fun <reified T : Any> GithubHttpClient.request(request: GithubRequest): Result<T> {
    return this.request(request, T::class)
}
