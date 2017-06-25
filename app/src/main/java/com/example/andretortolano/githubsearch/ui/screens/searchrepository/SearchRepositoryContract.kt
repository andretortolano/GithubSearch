package com.example.andretortolano.githubsearch.ui.screens.searchrepository

import com.example.andretortolano.githubsearch.api.github.responses.Repository
import com.example.andretortolano.githubsearch.ui.screens.BasePresenter
import com.example.andretortolano.githubsearch.ui.screens.BaseView

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
