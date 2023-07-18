package com.donatas.dprofile.http.models

enum class TokenType(val value: String) {
    BEARER("Bearer")
}

data class Token(
    val type: TokenType,
    val value: String
) {
    fun get() = "${type.value} $value"
}
