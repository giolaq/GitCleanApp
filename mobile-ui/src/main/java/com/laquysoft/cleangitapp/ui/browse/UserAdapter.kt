package com.laquysoft.cleangitapp.ui.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.laquysoft.cleangitapp.ui.R
import com.laquysoft.cleangitapp.ui.model.UserViewModel
import kotlinx.android.synthetic.main.item_user.view.*
import javax.inject.Inject

class UserAdapter @Inject constructor(): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var users: List<UserViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position], clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    internal var clickListener: (UserViewModel) -> Unit = { _ ->  }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(user: UserViewModel, clickListener: (UserViewModel) -> Unit) {
            itemView.text_name.text = user.login
            Glide.with(itemView.image_avatar.context)
                    .load(user.avatarUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(itemView.image_avatar)
            itemView.setOnClickListener { clickListener(user)}
        }
    }

}
