package com.example.andretortolano.githubsearch.api.github.responses

data class RepositoryResult(
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: List<Repository>
)
