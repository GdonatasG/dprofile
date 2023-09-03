package com.donatas.dprofile.githubservices.repository

import com.donatas.dprofile.githubservices.user.DefaultUserService
import com.donatas.dprofile.githubservices.user.UserService
import com.donatas.dprofile.githubservices.utils.createGithubHttpClient
import com.donatas.dprofile.test.runTest
import kotlin.test.Test

class UserServiceSmokeTest {
    @Test
    fun `test getUser`() = runTest {
        val sut = makeSUT()

        val response = sut.getUser("GdonatasG")

        response.onSuccess {
            println(it.toString())
        }.onFailure {
            it.printStackTrace()
        }
    }

    private fun makeSUT(): UserService = DefaultUserService(client = createGithubHttpClient())
}
