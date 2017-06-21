package com.example.andretortolano.githubsearch.api

import com.example.andretortolano.githubsearch.api.response.Repository
import com.example.andretortolano.githubsearch.api.response.RepositoryResult
import com.example.andretortolano.githubsearch.api.response.User
import com.example.andretortolano.githubsearch.api.response.UserResult
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

public interface GithubService {

  @GET("search/users") // https://developer.github.com/v3/search/#search-users
  fun searchUser(@Query("q") query: String, @Query("page") page: Int = 1, @Query("per_page") perPage: Int = 10) : Observable<UserResult>

  @GET("users/{username}") // https://developer.github.com/v3/users/#get-a-single-user
  fun getUser(@Path("username") username: String) : Observable<User>

  @GET("search/repositories") // https://developer.github.com/v3/search/#search-repositories
  fun searchRepository(@Query("q") query: String, @Query("page") page: Int = 1, @Query("per_page") perPage: Int = 10) : Observable<RepositoryResult>

  @GET("repos/{owner}/{repository}") // https://developer.github.com/v3/repos/#get
  fun getRepository(@Path("owner") owner: String, @Path("repository") repository: String) : Observable<Repository>

  companion object {
    fun create(): GithubService {
      return Retrofit.Builder()
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl("https://api.github.com/")
          .build()
          .create(GithubService::class.java)
    }
  }
}