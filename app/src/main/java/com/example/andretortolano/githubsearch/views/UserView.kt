package com.example.andretortolano.githubsearch.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andretortolano.githubsearch.R
import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.contracts.UserContract
import com.example.andretortolano.githubsearch.presenters.UserPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user.*

class UserView : Fragment(), UserContract.View {

    private lateinit var mPresenter: UserContract.Presenter

    private lateinit var mLogin: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter = UserPresenter(this, GithubService())
        mLogin = arguments.getString(USER_LOGIN)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater!!.inflate(R.layout.fragment_user, container, false)!!

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.getUser(mLogin)
    }

    override fun showProgress() {
        progress_view.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_view.visibility = View.GONE
    }

    override fun showUser(user: User) {
        user_name.text = user.name
        Picasso.with(context)
                .load(user.avatarUrl)
                .placeholder(R.drawable.harrypotter_cat)
                .into(user_avatar)
    }

    companion object {
        var USER_LOGIN = "BUNDLE_USER_LOGIN"

        fun newInstance(login: String): UserView {
            val fragment: UserView = UserView()

            val args: Bundle = Bundle()
            args.putString(USER_LOGIN, login)
            fragment.arguments = args
            return fragment
        }
    }
}
