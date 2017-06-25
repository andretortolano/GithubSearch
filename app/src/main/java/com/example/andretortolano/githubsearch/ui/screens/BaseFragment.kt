package com.example.andretortolano.githubsearch.ui.screens

import android.support.v4.app.Fragment
import android.R.attr.duration
import android.os.Bundle
import android.widget.Toast



abstract class BaseFragment<T> : Fragment(), BaseView<T> where T : BasePresenter {

    protected val BACK_STACK_NAME = "VIEWS_BACK_STACK"

    protected abstract var mPresenter: T

    override fun onStart() {
        super.onStart()
        mPresenter.start()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.stop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun showErrorMessage(message: String) {
        showToast(message)
    }

    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
