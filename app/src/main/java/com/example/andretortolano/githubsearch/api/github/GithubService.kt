package com.example.andretortolano.githubsearch.api.github

import android.util.Log
import com.example.andretortolano.githubsearch.BuildConfig
import com.example.andretortolano.githubsearch.api.github.responses.Repository
import com.example.andretortolano.githubsearch.api.github.responses.RepositoryResult
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.api.github.responses.UserResult
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GithubService {

    fun getUser(username: String): Observable<User> {
        return getInstance().getUser(username)
                .doOnError { error -> Log.e("Error", error.message) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchUsers(search: String): Observable<UserResult> {
        return getInstance().searchUser(search)
                .doOnError { error -> Log.e("Error", error.message) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRepository(owner: String, repos: String): Observable<Repository> {
        return getInstance().getRepository(owner, repos)
                .doOnError { error -> Log.e("Error", error.message) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchRepos(search: String): Observable<RepositoryResult> {
        return getInstance().searchRepository(search)
                .doOnError { error -> Log.e("Error", error.message) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        private var sInstance: GithubServiceAPI? = null

        fun getInstance(): GithubServiceAPI {
            if (sInstance == null) {
                val gson = GsonBuilder().setLenient().create()
                val retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl("https://api.github.com/")

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
