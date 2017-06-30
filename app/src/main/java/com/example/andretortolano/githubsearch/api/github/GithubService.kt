package com.example.andretortolano.githubsearch.api.github

import com.example.andretortolano.githubsearch.BuildConfig
import com.example.andretortolano.githubsearch.api.github.responses.Repository
import com.example.andretortolano.githubsearch.api.github.responses.RepositoryResult
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.api.github.responses.UserResult
import com.google.gson.GsonBuilder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class GithubService @Inject constructor(){

    fun getUser(username: String): Single<User> {
        return getInstance().getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchUsers(search: String?): Single<UserResult> {
        val result = if (search == null) {
            getInstance().searchUserSorted("type:user", "followers", "desc")
        } else {
            getInstance().searchUser(query = search)
        }

        return result
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRepository(owner: String, repos: String): Single<Repository> {
        return getInstance().getRepository(owner, repos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchRepositories(search: String?): Single<RepositoryResult> {
        val result = if (search == null) {
            getInstance().searchRepositorySorted("star:>=1", "stars", "desc")
        } else {
            getInstance().searchRepository(query = search)
        }

        return result
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        private var sInstance: GithubServiceAPI? = null

        private val API_URL: String = "https://api.github.com/"

        fun getInstance(): GithubServiceAPI {
            if (sInstance == null) {
                val gson = GsonBuilder().setLenient().create()
                val retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl(API_URL)

                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.level = HttpLoggingInterceptor.Level.BODY

                    val httpClient = OkHttpClient.Builder()
                    httpClient.addInterceptor(logging)
                    retrofit.client(httpClient.build())
                }

                sInstance = retrofit.build().create(GithubServiceAPI::class.java)
            }
            return sInstance!!
        }
    }
}
