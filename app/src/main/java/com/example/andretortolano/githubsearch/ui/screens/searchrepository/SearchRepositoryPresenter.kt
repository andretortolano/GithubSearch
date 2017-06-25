package com.example.andretortolano.githubsearch.ui.screens.searchrepository

import com.example.andretortolano.githubsearch.api.github.GithubService
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException

class SearchRepositoryPresenter(val repositoryView: SearchRepositoryContract.View, val githubService: GithubService) : SearchRepositoryContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var mCurrentSearch: String? = null;

    override fun start() {
        searchRepositories(null)
    }

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun onCloseActionSearch() {
        if (mCurrentSearch != null) {
            searchRepositories(null)
        }
    }

    override fun searchRepositories(search: String?) {
        repositoryView.showProgress()
        val disposable = githubService.searchRepositories(search)
                .subscribe { reposResult, error ->
                    if(reposResult != null) {
                        mCurrentSearch = search
                        repositoryView.showRepositories(reposResult.items)
                    } else if (error is HttpException) {
                        repositoryView.showErrorMessage(error.message())
                    }
                    repositoryView.hideProgress()
                }
        compositeDisposable.add(disposable)
    }
}
