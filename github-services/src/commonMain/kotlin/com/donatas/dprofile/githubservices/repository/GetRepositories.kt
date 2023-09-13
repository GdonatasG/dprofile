package com.donatas.dprofile.githubservices.repository

import com.donatas.dprofile.githubservices.common.Config
import com.donatas.dprofile.githubservices.common.Order
import com.donatas.dprofile.githubservices.common.PageBuilder
import com.donatas.dprofile.githubservices.common.Pageable
import com.donatas.dprofile.githubservices.common.request.GithubRequest
import com.donatas.dprofile.http.models.HttpMethod
import com.donatas.dprofile.http.models.HttpRequest
import com.donatas.dprofile.http.models.QueryParams

class GetRepositories : GithubRequest, Pageable<GetRepositories> {

    private var query: String = ""
    private var language: Language? = null
    private var order: Order? = null
    private var sort: SortField? = null
    private var user: String? = null
    private var pageBuilder: PageBuilder? = null

    private fun getParams(): QueryParams {
        if (query.isNotBlank() || language != null || user != null) {
            var queryParams: QueryParams = QueryParams()
            val query: String = buildString {
                append(query)
                language?.let {
                    append("language:${it.value}")
                }
                user?.let {
                    append("+user:$it")
                }
            }

            queryParams.put("q", query, encoded = true)

            order?.let {
                queryParams.put("order", it.value, encoded = false)
            }

            sort?.let {
                queryParams.put("sort", it.value, encoded = false)
            }

            pageBuilder?.let {
                queryParams += it.build()
            }

            return queryParams
        } else {
            throw IllegalArgumentException(
                "Provide at least one of the following parameters: " +
                        "query (Not blank), language or user"
            )
        }
    }

    override fun build(): HttpRequest = HttpRequest(
        method = HttpMethod.GET,
        endpoint = Config.SEARCH_ENDPOINT + "/repositories",
        params = getParams(),
        headers = mapOf(
            "Accept" to "application/vnd.github+json"
        )
    )

    fun query(value: String): GetRepositories {
        this.query = value

        return this
    }

    fun language(value: Language): GetRepositories {
        this.language = value

        return this
    }

    fun user(value: String): GetRepositories {
        this.user = value

        return this
    }

    fun order(order: Order, byField: SortField): GetRepositories {
        this.order = order
        this.sort = byField

        return this
    }

    override fun page(pageBuilder: PageBuilder.() -> Unit): GetRepositories {
        this.pageBuilder = PageBuilder().apply(pageBuilder)

        return this
    }

    enum class Language(internal val value: String) {
        JAVA("Java"), JAVASCRIPT("JavaScript"), HTML("HTML"), CSS("CSS"), SHELL("Shell"), PYTHON("Python"), TYPESCRIPT(
            "TypeScript"
        ),
        KOTLIN("Kotlin"), C_PLUS_PLUS("C++"), C("C"), GO("Go"), PHP("PHP"), RUBY("Ruby"), DART("Dart")
    }

    enum class SortField(internal val value: String) {
        UPDATED("updated")
    }
}
