package com.example.andretortolano.githubsearch.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andretortolano.githubsearch.R
import com.example.andretortolano.githubsearch.api.github.responses.Repository
import com.example.andretortolano.githubsearch.contracts.SearchRepositoryContract

class SearchRepositoryView : Fragment(), SearchRepositoryContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_search_repository, container, false)
    }

    override fun onResume() {
        super.onResume()
        Log.i("TAGFrag", "onResume")
    }

    override fun showRepositories(users: List<Repository>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
