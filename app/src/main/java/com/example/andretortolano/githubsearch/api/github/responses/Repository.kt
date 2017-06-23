package com.example.andretortolano.githubsearch.api.github.responses

import com.google.gson.annotations.SerializedName

data class Repository(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("owner") val owner: User,
        @SerializedName("private") val private: Boolean,
        @SerializedName("description") val description: String,
        @SerializedName("url") val url: String,
        @SerializedName("stargazers_count") val starsCount: Int,
        @SerializedName("watchers") val watchers: Int,
        @SerializedName("language") val primaryLanguage: String,
        @SerializedName("default_branch") val defaultBranch: String,
        @SerializedName("score") val score: Float
)
