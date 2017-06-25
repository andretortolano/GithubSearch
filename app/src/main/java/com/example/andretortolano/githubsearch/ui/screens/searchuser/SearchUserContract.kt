package com.example.andretortolano.githubsearch.ui.screens.searchuser

import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.ui.screens.BasePresenter
import com.example.andretortolano.githubsearch.ui.screens.BaseView

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
