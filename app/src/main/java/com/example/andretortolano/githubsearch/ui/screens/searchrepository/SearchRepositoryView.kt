package com.example.andretortolano.githubsearch.ui.screens.searchrepository

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.example.andretortolano.githubsearch.R
import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.Repository
import com.example.andretortolano.githubsearch.ui.components.CustomRecyclerViewListener
import com.example.andretortolano.githubsearch.ui.components.RepositoryRecyclerAdapter
import com.example.andretortolano.githubsearch.ui.screens.BaseFragment
import com.example.andretortolano.githubsearch.ui.screens.showrepository.ShowRepositoryView
import kotlinx.android.synthetic.main.fragment_search_repository.*

class SearchRepositoryView : BaseFragment<SearchRepositoryContract.Presenter>(), SearchRepositoryContract.View {

    override lateinit var mPresenter: SearchRepositoryContract.Presenter

    private var mAdapter: RepositoryRecyclerAdapter = RepositoryRecyclerAdapter(ArrayList<Repository>(), object : CustomRecyclerViewListener<Repository> {
        override fun onItemSelect(item: Repository) {
            openRepositoryDetails(item)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter = SearchRepositoryPresenter(this, GithubService())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater!!.inflate(R.layout.fragment_search_repository, container, false)!!

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = mAdapter
    }

    override fun showRepositories(repos: List<Repository>) {
        mAdapter.repositoryList.clear()
        mAdapter.repositoryList.addAll(repos)
        mAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        progress_view.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_view.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        val searchActionItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchActionItem) as SearchView
        searchView.queryHint = getString(R.string.repository_view_search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?) = false

            override fun onQueryTextSubmit(query: String?): Boolean {
                mPresenter.searchRepositories(query);
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

    override fun openRepositoryDetails(repository: Repository) {
        val frag: ShowRepositoryView = ShowRepositoryView.newInstance(repository.owner.login, repository.name)
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, frag)
                .addToBackStack(BACK_STACK_NAME)
                .commit()
    }
}
