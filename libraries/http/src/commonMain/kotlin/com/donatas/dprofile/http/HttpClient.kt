@file:Suppress("NAME_SHADOWING", "EXTENSION_SHADOWED_BY_MEMBER")

package com.donatas.dprofile.http

import com.donatas.dprofile.http.exceptions.ConnectTimeoutException
import com.donatas.dprofile.http.exceptions.UnresolvedAddressException
import com.donatas.dprofile.http.exceptions.UrlEncodingException
import com.donatas.dprofile.http.interceptors.HttpLoggingInterceptor
import com.donatas.dprofile.http.interceptors.Interceptor
import com.donatas.dprofile.http.ktor.KtorHttpClient
import com.donatas.dprofile.http.ktor.createKtorHttpClient
import com.donatas.dprofile.http.models.HttpRequest
import com.donatas.dprofile.http.models.HttpResponse
import com.donatas.dprofile.logger.Logger
import kotlin.coroutines.cancellation.CancellationException

interface HttpClient {
    companion object {
        fun create(
            ignoreTLS: Boolean = false,
            baseUrl: String = "",
        ): HttpClient = KtorHttpClient(
            client = createKtorHttpClient(ignoreTLS), baseUrl = baseUrl
        )
    }

    @Throws(
        CancellationException::class,
        UrlEncodingException::class,
        UnresolvedAddressException::class,
        ConnectTimeoutException::class
    )
    suspend fun request(httpRequest: HttpRequest): HttpResponse
}

fun HttpClient.addLoggingInterceptor(
    level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BASIC, logger: Logger
): HttpClient = HttpLoggingInterceptor(this, level, logger)

fun HttpClient.intercept(
    body: suspend (client: HttpClient) -> Unit
): HttpClient = Interceptor(this) { body(this) }
