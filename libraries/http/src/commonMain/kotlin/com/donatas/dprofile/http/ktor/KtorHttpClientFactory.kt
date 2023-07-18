package com.donatas.dprofile.http.ktor

import io.ktor.client.HttpClient

internal expect fun createKtorHttpClient(ignoreTLS: Boolean = false): HttpClient
