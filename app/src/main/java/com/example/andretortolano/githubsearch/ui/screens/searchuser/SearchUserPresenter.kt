package com.example.andretortolano.githubsearch.ui.screens.searchuser

import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.UserResult
import com.example.andretortolano.githubsearch.debug.Logger
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException


class SearchUserPresenter(val userView: SearchUserContract.View, val githubService: GithubService) : SearchUserContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var mCurrentSearch: String? = null

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun start() {
        searchUsers(null)
    }

    override fun onCloseActionSearch() {
        if (mCurrentSearch != null) {
            searchUsers(null)
        }
    }

    override fun searchUsers(search: String?) {
        userView.showProgress()
        val disposable = githubService.searchUsers(search)
                .subscribe { userResult, error ->
                    if(userResult != null) {
                        mCurrentSearch = search
                        userView.showUsers(userResult.items)
                    } else if(error is HttpException) {
                        userView.showErrorMessage(error.message())
                    }
                    userView.hideProgress()
                }
        compositeDisposable.add(disposable)
    }
}
