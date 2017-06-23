package com.example.andretortolano.githubsearch.presenters

import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.Repository
import com.example.andretortolano.githubsearch.contracts.SearchRepositoryContract
import com.example.andretortolano.githubsearch.debug.Logger

class SearchRepositoryPresenter(val repositoryView: SearchRepositoryContract.View, val githubService: GithubService) : SearchRepositoryContract.Presenter {

    var mCurrentSearch: String? = null;

    override fun start() {
        searchRepositories(null)
    }

    override fun onCloseActionSearch() {
        if (mCurrentSearch != null) {
            searchRepositories(null)
        }
    }

    override fun searchRepositories(search: String?) {
        repositoryView.showProgress()
        githubService.searchRepositories(search)
                .subscribe { reposResult ->
                    run {
                        mCurrentSearch = search
                        repositoryView.showRepositories(reposResult.items)
                        repositoryView.hideProgress()
                    }
                }
    }
}
