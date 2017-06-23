package com.example.andretortolano.githubsearch.presenters

import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.contracts.SearchUserContract
import com.example.andretortolano.githubsearch.debug.Logger

class SearchUserPresenter(val userView: SearchUserContract.View, val githubService: GithubService) : SearchUserContract.Presenter {

    var mCurrentSearch: String? = null

    override fun start() {
        searchUsers(null)
    }

    override fun onCloseActionSearch() {
        if (mCurrentSearch != null) {
            searchUsers(null)
        }
    }

    override fun searchUsers(search: String?) {
        userView.showProgress()
        githubService.searchUsers(search)
                .subscribe { userResult ->
                    run {
                        mCurrentSearch = search
                        userView.showUsers(userResult.items)
                        userView.hideProgress()
                    }
                }
    }
}
