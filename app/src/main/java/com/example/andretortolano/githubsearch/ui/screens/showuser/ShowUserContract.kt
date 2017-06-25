package com.example.andretortolano.githubsearch.ui.screens.showuser

import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.ui.screens.BasePresenter
import com.example.andretortolano.githubsearch.ui.screens.BaseView

interface ShowUserContract {
    interface View : BaseView<Presenter> {
        fun showProgress()

        fun hideProgress()

        fun showUser(user: User)
    }

    interface Presenter : BasePresenter {

    }
}
