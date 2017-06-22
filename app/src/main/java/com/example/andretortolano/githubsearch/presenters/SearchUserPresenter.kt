package com.example.andretortolano.githubsearch.presenters

import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.contracts.SearchUserContract

class SearchUserPresenter(val userView: SearchUserContract.View, val githubService: GithubService) : SearchUserContract.Presenter {

    override fun resume() {
        githubService.getPopularUsers()
            .subscribe {
                userResult -> userView.showUsers(userResult.items)
            }
    }

    override fun openUserDetails(user: User) {
        // TODO Open new Fragment
    }

    override fun searchUsers(search: String) {
        githubService.searchUsers(search)
            .map { userResult -> userResult.items }
            .subscribe { userList -> userView.showUsers(userList) }
    }
}
