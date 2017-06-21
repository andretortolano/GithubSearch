package com.example.andretortolano.githubsearch.api.response

import com.google.gson.annotations.SerializedName

data class UserResult (
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<User>
)

/**
 * Equivalent of user data class in kotlin
 */
data class User(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Long,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("type") val type: String,
    @SerializedName("site_admin") val siteAdmin: Boolean,
    @SerializedName("score") val score: Int
)
