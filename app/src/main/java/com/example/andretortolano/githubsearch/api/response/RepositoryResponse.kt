package com.example.andretortolano.githubsearch.api.response

import com.google.gson.annotations.SerializedName

data class RepositoryResult (
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Repository>
)

/**
 * Equivalent of user data class in kotlin
 */
data class Repository (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("owner") val owner: User,
    @SerializedName("private") val private: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("watchers") val watchers: Int,
    @SerializedName("default_branch") val defaultBranch: String,
    @SerializedName("score") val score: Int
)