package com.example.andretortolano.githubsearch.ui.screens.showrepository

import com.example.andretortolano.githubsearch.api.github.GithubService
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException

class ShowRepositoryPresenter(val repositoryView: ShowRepositoryContract.View,
                              val githubService: GithubService,
                              val owner: String, val repositoryName: String) : ShowRepositoryContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun start() {
        repositoryView.showProgress()
        val disposable = githubService.getRepository(owner, repositoryName)
                .subscribe { repository, error ->
                    if(repository != null) {
                        repositoryView.showRepository(repository)
                    } else if(error is HttpException) {
                        repositoryView.showErrorMessage(error.message())
                    }
                    repositoryView.hideProgress()
                }

        compositeDisposable.add(disposable)
    }
}
