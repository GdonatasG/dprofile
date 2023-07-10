package com.donatas.dprofile.http.ktor

import com.donatas.dprofile.exceptions.*
import com.donatas.dprofile.http.ktor.KtorHttpClient
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.*
import io.ktor.util.network.UnresolvedAddressException
import platform.Foundation.*

internal actual fun createKtorHttpClient(ignoreTLS: Boolean): KtorHttpClient =
    KtorHttpClient(Darwin) {
        engine {
            if (ignoreTLS) {
                handleChallenge { _, _, challenge, completionHandler ->
                    if (challenge.protectionSpace.authenticationMethod == NSURLAuthenticationMethodServerTrust) {
                        val trust = challenge.protectionSpace.serverTrust
                        // NSURLSessionAuthChallengeUseCredential = 0
                        // NSURLSessionAuthChallengePerformDefaultHandling = 1
                        completionHandler(0, NSURLCredential.create(trust))
                    } else completionHandler(1, null)
                }
            }
        }

        HttpResponseValidator {
            handleResponseExceptionWithRequest { error, _ ->
                val exception = error as? DarwinHttpRequestException ?: return@handleResponseExceptionWithRequest
                when (exception.origin.code) {
                    NSURLErrorNotConnectedToInternet -> {
                        val nsError = exception.origin.underlyingErrors.firstOrNull() as? NSError
                            ?: throw UnresolvedAddressException()
                        val cause = nsError.userInfo["_NSURLErrorNWPathKey"] ?: throw UnresolvedAddressException()
                        if (cause.toString().contains("Local network", ignoreCase = true))
                            throw UnresolvedAddressException()
                        else
                            throw UnresolvedAddressException()
                    }
                    NSURLErrorCannotConnectToHost -> throw UnresolvedAddressException()
                    NSURLErrorNetworkConnectionLost ->  throw io.ktor.client.network.sockets.ConnectTimeoutException("Connection timed out!", null)
                    NSURLErrorTimedOut -> throw io.ktor.client.network.sockets.ConnectTimeoutException("Connection timed out!", null)
                }
            }
        }
    }
