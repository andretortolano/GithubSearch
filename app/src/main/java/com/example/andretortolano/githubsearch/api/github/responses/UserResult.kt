package com.example.andretortolano.githubsearch.api.github.responses

data class UserResult (
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: List<User>
)