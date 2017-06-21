package com.example.andretortolano.githubsearch.model

data class UserModel(
    val login: String,
    val id: Long,
    val avatarUrl: String,
    val url: String,
    val type: String,
    val siteAdmin: Boolean,
    val score: Int
)
