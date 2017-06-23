package com.example.andretortolano.githubsearch.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.example.andretortolano.githubsearch.R
import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.components.CustomRecyclerViewListener
import com.example.andretortolano.githubsearch.components.UserRecyclerAdapter
import com.example.andretortolano.githubsearch.contracts.SearchUserContract
import com.example.andretortolano.githubsearch.presenters.SearchUserPresenter
import kotlinx.android.synthetic.main.fragment_search_user.*


class SearchUserView : Fragment(), SearchUserContract.View {
    private lateinit var mPresenter: SearchUserContract.Presenter

    private lateinit var mAdapter: UserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mPresenter = SearchUserPresenter(this, GithubService())

        mAdapter = UserRecyclerAdapter(ArrayList<User>(), object : CustomRecyclerViewListener<User> {
            override fun onItemSelect(item: User) {
                openUserDetails(item)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater!!.inflate(R.layout.fragment_search_user, container, false)!!

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = mAdapter
        mPresenter.start()
    }

    override fun showUsers(users: List<User>) {
        mAdapter.userList.clear()
        mAdapter.userList.addAll(users)
        mAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        progress_view.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_view.visibility = View.GONE
    }

    override fun openUserDetails(user: User) {
        val frag: UserView = UserView.newInstance(user.login)
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, frag)
                .addToBackStack("INFO_VIEW")
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        val searchActionItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchActionItem) as SearchView
        searchView.queryHint = getString(R.string.user_view_search_hint)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?) = false

            override fun onQueryTextSubmit(query: String?): Boolean {
                mPresenter.searchUsers(query);
                return false
            }
        })

        MenuItemCompat.setOnActionExpandListener(searchActionItem, object : MenuItemCompat.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?) = true

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                mPresenter.onCloseActionSearch()
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}
