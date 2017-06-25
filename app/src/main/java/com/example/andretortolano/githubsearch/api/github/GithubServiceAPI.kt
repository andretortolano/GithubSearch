package com.example.andretortolano.githubsearch.api.github

import com.example.andretortolano.githubsearch.api.github.responses.Repository
import com.example.andretortolano.githubsearch.api.github.responses.RepositoryResult
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.api.github.responses.UserResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GithubServiceAPI {

    @GET("search/users") // https://developer.github.com/v3/search/#search-users
    fun searchUser(@Query("q") query: String): Single<UserResult>

    @GET("search/users") // https://developer.github.com/v3/search/#search-users
    fun searchUserSorted(@Query("q") query: String, @Query("sort") sort: String, @Query("order") order: String): Single<UserResult>

    @GET("users/{username}") // https://developer.github.com/v3/users/#get-a-single-user
    fun getUser(@Path("username") username: String): Single<User>

    @GET("search/repositories") // https://developer.github.com/v3/search/#search-repositories
    fun searchRepository(@Query("q") query: String): Single<RepositoryResult>

    @GET("search/repositories") // https://developer.github.com/v3/search/#search-repositories
    fun searchRepositorySorted(@Query("q") query: String, @Query("sort") sort: String, @Query("order") order: String): Single<RepositoryResult>

    @GET("repos/{owner}/{repository}") // https://developer.github.com/v3/repos/#get
    fun getRepository(@Path("owner") owner: String, @Path("repository") repository: String): Single<Repository>
}
