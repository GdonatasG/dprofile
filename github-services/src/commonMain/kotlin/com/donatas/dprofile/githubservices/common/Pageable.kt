package com.donatas.dprofile.githubservices.common

import com.donatas.dprofile.http.models.QueryParams

class PageBuilder {
    var perPage: Int? = null
    var page: Int? = null

    internal fun build(): QueryParams = QueryParams().apply {
        perPage?.let {
            put("per_page", it, encoded = false)
        }
        page?.let {
            put("page", it, encoded = false)
        }
    }
}

interface Pageable<T> {
    fun page(pageBuilder: PageBuilder.() -> Unit): T
}
