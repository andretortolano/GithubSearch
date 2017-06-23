package com.example.andretortolano.githubsearch.contracts

import com.example.andretortolano.githubsearch.BasePresenter
import com.example.andretortolano.githubsearch.BaseView
import com.example.andretortolano.githubsearch.api.github.responses.User

interface SearchUserContract {
    interface View : BaseView<Presenter> {
        fun showProgress()

        fun hideProgress()

        fun showUsers(users: List<User>)

        fun openUserDetails(user: User)
    }

    interface Presenter : BasePresenter {
        fun searchUsers(search: String?)

        fun onCloseActionSearch()
    }
}
