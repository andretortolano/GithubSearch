package com.example.andretortolano.githubsearch.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andretortolano.githubsearch.R
import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.Repository
import com.example.andretortolano.githubsearch.contracts.RepositoryContract
import com.example.andretortolano.githubsearch.presenters.RepositoryPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_repository.*

class RepositoryView : Fragment(), RepositoryContract.View {

    private lateinit var mPresenter: RepositoryContract.Presenter

    private lateinit var mOwner: String

    private lateinit var mName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter = RepositoryPresenter(this, GithubService())
        mOwner = arguments.getString(OWNER)
        mName = arguments.getString(NAME)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater!!.inflate(R.layout.fragment_repository, container, false)!!

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.getRepository(mOwner, mName)
    }

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

        var NAME = "BUNDLE_NAME"

        fun newInstance(owner: String, name: String): RepositoryView {
            val fragment: RepositoryView = RepositoryView()

            val args: Bundle = Bundle()
            args.putString(OWNER, owner)
            args.putString(NAME, name)
            fragment.arguments = args
            return fragment
        }
    }
}
