package com.example.andretortolano.githubsearch.ui.components

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andretortolano.githubsearch.R
import com.example.andretortolano.githubsearch.api.github.responses.Repository
import kotlinx.android.synthetic.main.repository_list_item.view.*

class RepositoryRecyclerAdapter(var repositoryList: ArrayList<Repository>, var listener: CustomRecyclerViewListener<Repository>) : RecyclerView.Adapter<RepositoryRecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.repository_list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val view = holder?.itemView!!

        view.repository_name.text = repositoryList[position].fullName
        // TODO fill other fields

        view.setOnClickListener({
            _ ->
            listener.onItemSelect(repositoryList[position])
        })

    }

    override fun getItemCount() = repositoryList.size
}
