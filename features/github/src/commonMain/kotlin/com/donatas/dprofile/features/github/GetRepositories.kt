package com.donatas.dprofile.features.github

import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.loader.LoadingResult

interface GetRepositories {
    suspend operator fun invoke(page: Int, perPage: Int, login: String): LoadingResult<Repository>
}
