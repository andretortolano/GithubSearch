package com.example.andretortolano.githubsearch.ui.screens.showrepository

import android.os.Bundle
import android.view.*
import com.example.andretortolano.githubsearch.R
import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.Repository
import com.example.andretortolano.githubsearch.ui.screens.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_repository.*

class ShowRepositoryView : BaseFragment<ShowRepositoryContract.Presenter>(), ShowRepositoryContract.View {

    override lateinit var mPresenter: ShowRepositoryContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter = ShowRepositoryPresenter(this, GithubService(), arguments.getString(OWNER), arguments.getString(REPOSITORY_NAME))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater!!.inflate(R.layout.fragment_repository, container, false)!!

    override fun showProgress() {
        progress_view.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_view.visibility = View.GONE
    }

    override fun showRepository(repository: Repository) {
        repository_name.text = repository.name
        repository_owner.text = repository.owner.login
        Picasso.with(context)
                .load(repository.owner.avatarUrl)
                .placeholder(R.drawable.harrypotter_cat)
                .into(repository_owner_avatar)
    }

    companion object {
        var OWNER = "BUNDLE_OWNER"

        var REPOSITORY_NAME = "BUNDLE_REPOSITORY_NAME"

        fun newInstance(owner: String, name: String): ShowRepositoryView {
            val fragment: ShowRepositoryView = ShowRepositoryView()

            val args: Bundle = Bundle()
            args.putString(OWNER, owner)
            args.putString(REPOSITORY_NAME, name)
            fragment.arguments = args
            return fragment
        }
    }
}
