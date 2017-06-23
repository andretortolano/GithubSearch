package com.example.andretortolano.githubsearch.contracts

import com.example.andretortolano.githubsearch.BasePresenter
import com.example.andretortolano.githubsearch.BaseView
import com.example.andretortolano.githubsearch.api.github.responses.User

interface UserContract {
    interface View : BaseView<Presenter> {
        fun showProgress()

        fun hideProgress()

        fun showUser(user: User)
    }

    interface Presenter : BasePresenter {
        fun getUser(login: String)
    }
}
