package com.example.andretortolano.githubsearch.contracts

import com.example.andretortolano.githubsearch.BasePresenter
import com.example.andretortolano.githubsearch.BaseView
import com.example.andretortolano.githubsearch.api.github.responses.Repository

interface SearchRepositoryContract {
    interface View : BaseView<Presenter> {
        fun showProgress()

        fun hideProgress()

        fun showRepositories(repos: List<Repository>)

        fun openRepositoryDetails(repository: Repository)
    }

    interface Presenter : BasePresenter {
        fun searchRepositories(search: String?)

        fun onCloseActionSearch()
    }
}
