package com.example.andretortolano.githubsearch.ui.screens.showuser

import com.example.andretortolano.githubsearch.api.github.GithubService
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException

class ShowUserPresenter(val showUserView: ShowUserContract.View,
                        val githubService: GithubService,
                        val login: String) : ShowUserContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun start() {
        showUserView.showProgress()
        val disposable = githubService.getUser(login)
                .subscribe { user, error ->
                    if(user != null) {
                        showUserView.showUser(user)
                    } else if(error is HttpException) {
                        showUserView.showErrorMessage(error.message())
                    }
                    showUserView.hideProgress()
                }
        compositeDisposable.add(disposable)
    }
}
