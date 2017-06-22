package com.example.andretortolano.githubsearch.presenters

import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.contracts.SearchUserContract

class SearchUserPresenter(userView: SearchUserContract.View, githubService: GithubService) : SearchUserContract.Presenter {
    override fun openUserDetails(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchUsers(search: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
