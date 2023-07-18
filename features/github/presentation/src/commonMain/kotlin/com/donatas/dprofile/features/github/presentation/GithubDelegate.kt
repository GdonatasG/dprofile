package com.donatas.dprofile.features.github.presentation

interface GithubDelegate {
    fun onSearch()
    fun onDetails(repoUrl: String)
}
