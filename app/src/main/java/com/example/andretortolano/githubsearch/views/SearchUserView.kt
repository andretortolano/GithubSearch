package com.example.andretortolano.githubsearch.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andretortolano.githubsearch.R
import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.components.UserRecyclerAdapter
import com.example.andretortolano.githubsearch.contracts.SearchUserContract
import com.example.andretortolano.githubsearch.presenters.SearchUserPresenter
import kotlinx.android.synthetic.main.fragment_search_user.*

class SearchUserView : Fragment(), SearchUserContract.View {

    private lateinit var mPresenter: SearchUserContract.Presenter

    private lateinit var mAdapter: UserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter = SearchUserPresenter(this, GithubService())

        mAdapter = UserRecyclerAdapter(ArrayList<User>())

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = mAdapter
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_search_user, container, false)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.resume()
    }

    override fun showUsers(users: List<User>) {
        mAdapter.userList.clear()
        mAdapter.userList.addAll(users)
        mAdapter.notifyDataSetChanged()
    }
}
