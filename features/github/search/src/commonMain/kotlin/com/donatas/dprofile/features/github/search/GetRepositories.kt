package com.donatas.dprofile.features.github.search

import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.loader.LoadingResult

interface GetRepositories {
    suspend operator fun invoke(
        page: Int,
        perPage: Int,
        globalSearch: Boolean,
        query: String,
        order: Order
    ): LoadingResult<Repository>
}
