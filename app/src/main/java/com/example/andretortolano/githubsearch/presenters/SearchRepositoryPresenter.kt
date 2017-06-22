package com.example.andretortolano.githubsearch.presenters

import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.contracts.SearchRepositoryContract

class SearchRepositoryPresenter(val repositoryView: SearchRepositoryContract.View, val githubService: GithubService) : SearchRepositoryContract.Presenter {
    override fun resume() {

    }

    override fun searchRepositories(search: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openRepositoryDetails(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
