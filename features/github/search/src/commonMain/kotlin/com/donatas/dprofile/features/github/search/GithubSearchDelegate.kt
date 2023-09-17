package com.donatas.dprofile.features.github.search

interface GithubSearchDelegate {
    fun onBack()
    fun onFilter()
    fun onDetails(repoUrl: String)
}
