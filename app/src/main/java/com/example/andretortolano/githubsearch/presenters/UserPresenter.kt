package com.example.andretortolano.githubsearch.presenters

import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.contracts.UserContract

class UserPresenter(val userView: UserContract.View, val githubService: GithubService) : UserContract.Presenter {
    override fun start() {
        // Do Nothing
    }

    override fun getUser(login: String) {
        userView.showProgress()
        githubService.getUser(login)
                .subscribe { user ->
                    run {
                        userView.showUser(user)
                        userView.hideProgress()
                    }
                }
    }
}
