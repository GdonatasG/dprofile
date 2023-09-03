package com.donatas.dprofile.composition.di

import com.donatas.dprofile.githubservices.common.Config
import com.donatas.dprofile.githubservices.common.http.DefaultGithubHttpClient
import com.donatas.dprofile.githubservices.common.http.GithubHttpClient
import com.donatas.dprofile.githubservices.common.http.interceptor.GithubAPIVersion
import com.donatas.dprofile.githubservices.common.http.interceptor.addAuthorizationHandler
import com.donatas.dprofile.githubservices.common.http.interceptor.baseUrl
import com.donatas.dprofile.githubservices.common.http.interceptor.specifyAPIVersion
import com.donatas.dprofile.http.HttpClient
import com.donatas.dprofile.http.addLoggingInterceptor
import com.donatas.dprofile.http.interceptors.HttpLoggingInterceptor
import com.donatas.dprofile.http.models.Token
import com.donatas.dprofile.http.models.TokenType
import com.donatas.dprofile.logger.NapierLogger
import org.koin.core.module.Module
import org.koin.dsl.module

internal val networkingModule: Module = module {
    single<HttpClient> {
        HttpClient.create()
            .addLoggingInterceptor(HttpLoggingInterceptor.Level.BASIC, NapierLogger(tag = "HttpClient"))
    }

    single<GithubHttpClient> {
        DefaultGithubHttpClient(
            client = get<HttpClient>()
        )
            .baseUrl(Config.BASE_URL)
            .addAuthorizationHandler {
                Token(type = TokenType.BEARER, value = "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")
            }
            .specifyAPIVersion(version = GithubAPIVersion.NOVEMBER_28_2022)
    }


}
