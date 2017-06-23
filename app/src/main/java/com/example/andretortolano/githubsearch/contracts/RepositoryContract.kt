package com.example.andretortolano.githubsearch.contracts

import com.example.andretortolano.githubsearch.BasePresenter
import com.example.andretortolano.githubsearch.BaseView
import com.example.andretortolano.githubsearch.api.github.responses.Repository
import com.example.andretortolano.githubsearch.api.github.responses.User

interface RepositoryContract {
    interface View : BaseView<Presenter> {
        fun showProgress()

        fun hideProgress()

        fun showRepository(repository: Repository)
    }

    interface Presenter : BasePresenter {
        fun getRepository(owner: String, name: String)
    }
}
