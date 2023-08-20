package com.donatas.dprofile.githubservices.utils

import com.donatas.dprofile.githubservices.common.Config
import com.donatas.dprofile.githubservices.common.http.DefaultGithubHttpClient
import com.donatas.dprofile.githubservices.common.http.GithubHttpClient
import com.donatas.dprofile.githubservices.common.http.interceptor.GithubAPIVersion
import com.donatas.dprofile.githubservices.common.http.interceptor.addAuthorizationHandler
import com.donatas.dprofile.githubservices.common.http.interceptor.baseUrl
import com.donatas.dprofile.githubservices.common.http.interceptor.specifyAPIVersion
import com.donatas.dprofile.http.HttpClient
import com.donatas.dprofile.http.addLoggingInterceptor
import com.donatas.dprofile.http.models.Token
import com.donatas.dprofile.http.models.TokenType
import com.donatas.dprofile.logger.PrintLogger

private fun createHttpClient(): HttpClient = HttpClient.create(ignoreTLS = true)
    .addLoggingInterceptor(logger = PrintLogger("TestHttpClient"))

internal fun createGithubHttpClient(): GithubHttpClient = DefaultGithubHttpClient(
    client = createHttpClient()
).baseUrl(Config.BASE_URL).addAuthorizationHandler {
    Token(type = TokenType.BEARER, value = throw RuntimeException("Pass token"))
}.specifyAPIVersion(version = GithubAPIVersion.NOVEMBER_28_2022)
