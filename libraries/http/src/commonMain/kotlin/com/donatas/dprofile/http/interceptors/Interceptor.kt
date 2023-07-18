package com.donatas.dprofile.http.interceptors

import com.donatas.dprofile.http.HttpClient
import com.donatas.dprofile.http.models.HttpRequest
import com.donatas.dprofile.http.models.HttpResponse

class Interceptor(
    private val client: HttpClient,
    private val body: suspend () -> Unit
) : HttpClient {

    override suspend fun request(httpRequest: HttpRequest): HttpResponse {
        return intercept {
            client.request(httpRequest)
        }
    }


    private suspend fun intercept(
        request: suspend () -> HttpResponse
    ): HttpResponse {
        body()

        return request()
    }
}
