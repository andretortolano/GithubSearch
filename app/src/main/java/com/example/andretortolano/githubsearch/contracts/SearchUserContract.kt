package com.example.andretortolano.githubsearch.contracts

import com.example.andretortolano.githubsearch.BasePresenter
import com.example.andretortolano.githubsearch.BaseView
import com.example.andretortolano.githubsearch.api.github.responses.User

interface SearchUserContract {
    interface View : BaseView<Presenter> {
        fun showUsers(users: List<User>)
    }

    interface Presenter : BasePresenter {
        fun searchUsers(search: String)

        fun openUserDetails(user: User)
    }
}
