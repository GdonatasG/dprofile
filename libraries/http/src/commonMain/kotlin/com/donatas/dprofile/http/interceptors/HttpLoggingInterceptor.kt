package com.donatas.dprofile.http.interceptors

import com.donatas.dprofile.http.HttpClient
import com.donatas.dprofile.http.models.HttpRequest
import com.donatas.dprofile.http.models.HttpResponse
import com.donatas.dprofile.http.models.QueryParams
import com.donatas.dprofile.logger.LogType
import com.donatas.dprofile.logger.Logger

class HttpLoggingInterceptor(
    private val client: HttpClient,
    private val level: Level,
    private val logger: Logger,
) : HttpClient {

    @Suppress("TooGenericExceptionCaught", "TooGenericExceptionThrown")
    override suspend fun request(httpRequest: HttpRequest): HttpResponse {
        logger.log(message = "")

        val endpoint = httpRequest.endpoint + appendParams(httpRequest.params)

        logger.log(message = "--> ${httpRequest.method.name} $endpoint")

        if (level == Level.HEADERS) {
            logHeaders(headers = httpRequest.headers, prefix = "-->")
        }

        httpRequest.body?.let {
            logger.log(message = it)
        }

        logger.log(message = "--> END ${httpRequest.method.name}")

        return try {
            val response = client.request(httpRequest)

            logger.log(message = "<-- ${response.code}")

            if (level == Level.HEADERS) {
                logHeaders(headers = response.headers, prefix = "<--")
            }

            logger.log(message = response.body)

            logger.log(message = "<-- END HTTP")

            response

        } catch (exception: Exception) {
            logger.log(message = exception.message ?: "", type = LogType.ERROR, throwable = exception)
            throw exception
        }
    }

    private fun appendParams(queryParams: QueryParams?): String {
        var params = ""
        queryParams?.parameters?.forEach {
            if (it.value.isNotEmpty()) {
                params += if (params.isEmpty()) {
                    "?${it.key}=${it.value}"
                } else {
                    "&${it.key}=${it.value}"
                }
            }
        }

        return params
    }

    private fun logHeaders(headers: Map<String, String>, prefix: String) {
        if (headers.isEmpty()) return

        logger.log(message = "$prefix Headers")

        headers.forEach {
            logger.log(message = "${it.key}: ${it.value}")
        }

        logger.log(message = "$prefix Headers END")
    }

    enum class Level {
        /** Logs request and response with bodies */
        BASIC,

        /** Logs request and response with bodies including headers */
        HEADERS
    }
}
