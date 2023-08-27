package com.donatas.dprofile.githubservices.repository

import com.donatas.dprofile.githubservices.common.Order
import com.donatas.dprofile.githubservices.utils.createGithubHttpClient
import com.donatas.dprofile.test.runTest
import kotlin.test.Test

class RepositoryServiceSmokeTest {

    @Test
    fun `test getRepositories`() = runTest {
        val sut = makeSUT()

        val response = sut.getRepositories {
            user("GdonatasG")
            language(GetRepositories.Language.DART)
            order(order = Order.ASC, byField = GetRepositories.SortField.UPDATED)
            page {
                page = 3
                perPage = 4
            }
        }

        response.onSuccess {
            println(it.toString())
        }.onFailure {
            it.printStackTrace()
        }
    }


    private fun makeSUT(): RepositoryService = DefaultRepositoryService(client = createGithubHttpClient())
}
