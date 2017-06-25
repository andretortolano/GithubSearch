package com.example.andretortolano.githubsearch.ui.screens.showrepository

import com.example.andretortolano.githubsearch.api.github.responses.Repository
import com.example.andretortolano.githubsearch.ui.screens.BasePresenter
import com.example.andretortolano.githubsearch.ui.screens.BaseView

interface ShowRepositoryContract {
    interface View : BaseView<Presenter> {
        fun showProgress()

        fun hideProgress()

        fun showRepository(repository: Repository)
    }

    interface Presenter : BasePresenter {

    }
}
