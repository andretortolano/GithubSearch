package com.example.andretortolano.githubsearch.contracts

import com.example.andretortolano.githubsearch.BasePresenter
import com.example.andretortolano.githubsearch.BaseView
import com.example.andretortolano.githubsearch.api.github.responses.Repository
import com.example.andretortolano.githubsearch.api.github.responses.User

interface SearchRepositoryContract {
    interface View : BaseView<Presenter> {
        fun showRepositories(users: List<Repository>)
    }

    interface Presenter : BasePresenter {
        fun searchRepositories(search: String)

        fun openRepositoryDetails(user: User)
    }
}
