package com.example.andretortolano.githubsearch.presenters

import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.contracts.RepositoryContract

class RepositoryPresenter(val repositoryView: RepositoryContract.View, val githubService: GithubService) : RepositoryContract.Presenter {

    override fun start() {
        // Do Nothing
    }

    override fun getRepository(owner: String, name: String) {
        repositoryView.showProgress()
        githubService.getRepository(owner, name)
                .subscribe { repository ->
                    run {
                        repositoryView.showRepository(repository)
                        repositoryView.hideProgress()
                    }
                }
    }
}
