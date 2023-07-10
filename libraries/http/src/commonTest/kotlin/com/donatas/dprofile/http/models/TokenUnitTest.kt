package com.donatas.dprofile.http.models

import com.donatas.dprofile.http.models.Token
import com.donatas.dprofile.http.models.TokenType
import kotlin.test.Test
import kotlin.test.assertEquals

class TokenUnitTest {

    @Test
    fun test_get_concatenatesTokenTypeWithTokenCorrectly() {
        val tokenValue = "testToken"
        val testToken = Token(
            TokenType.BEARER,
            tokenValue
        )

        assertEquals("${TokenType.BEARER.value} $tokenValue", testToken.get())
    }
}
