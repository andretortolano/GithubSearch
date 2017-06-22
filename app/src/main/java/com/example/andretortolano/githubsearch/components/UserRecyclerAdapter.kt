package com.example.andretortolano.githubsearch.components

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andretortolano.githubsearch.R
import com.example.andretortolano.githubsearch.api.github.responses.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_list_item.view.*

class UserRecyclerAdapter(var userList: ArrayList<User>): RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>() {
  class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
    ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.user_list_item, parent, false))


  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    val view = holder?.itemView!!
    view.user_login.text = userList[position].login
    Logger.i(userList[position].avatarUrl)
    Picasso.with(view.context)
        .load(userList[position].avatarUrl)
        .placeholder(R.drawable.harrypotter_cat)
        .into(view.user_avatar)
  }

  override fun getItemCount() = userList.size
}