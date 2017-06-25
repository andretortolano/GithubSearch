package com.example.andretortolano.githubsearch.ui.screens.showuser

import android.os.Bundle
import android.view.*
import com.example.andretortolano.githubsearch.R
import com.example.andretortolano.githubsearch.api.github.GithubService
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.example.andretortolano.githubsearch.ui.screens.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user.*

class ShowUserView : BaseFragment<ShowUserContract.Presenter>(), ShowUserContract.View {

    override lateinit var mPresenter: ShowUserContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter = ShowUserPresenter(this, GithubService(), arguments.getString(USER_LOGIN))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater!!.inflate(R.layout.fragment_user, container, false)!!

    override fun showProgress() {
        progress_view.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_view.visibility = View.GONE
    }

    override fun showErrorMessage(message: String) {
        showToast(message)
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

        fun newInstance(login: String): ShowUserView {
            val fragment: ShowUserView = ShowUserView()

            val args: Bundle = Bundle()
            args.putString(USER_LOGIN, login)
            fragment.arguments = args
            return fragment
        }
    }
}
