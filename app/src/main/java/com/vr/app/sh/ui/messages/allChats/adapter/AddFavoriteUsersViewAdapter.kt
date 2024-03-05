package com.vr.app.sh.ui.messages.allChats.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.vr.app.sh.R
import com.vr.app.sh.data.api.NetworkService.Companion.BASE_URL
import com.vr.app.sh.domain.model.messages.FavoriteUser

class AddFavoriteUsersViewAdapter(val context: Context) : RecyclerView.Adapter<AddFavoriteUsersViewAdapter.ViewHolder>() {
    private var listener: Listener? = null
    private var usersList:List<FavoriteUser> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(users: List<FavoriteUser>) {
        this.usersList = users
        notifyDataSetChanged()
    }

    interface Listener {
        fun selectUser(favoriteUser: FavoriteUser)
    }

    override fun getItemCount():Int{
        return usersList.size
    }


    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_button_user_select, parent, false) as CardView
        return ViewHolder(cv)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView
        val user = usersList[position].user

        val userPhoto = cardView.findViewById<ShapeableImageView>(R.id.userPhoto)
        val userName = cardView.findViewById<TextView>(R.id.textName)
        val selectUser = cardView.findViewById<ImageView>(R.id.select_user)

        if (usersList[position].userId!=null){
            selectUser.visibility = View.VISIBLE
        }else{
            selectUser.visibility = View.GONE
        }

        Glide.with(context)
            .load(BASE_URL + "/Photo?id=" + user?.id)
            .placeholder(R.drawable.bg_with_radius_12dp)
            .into(userPhoto)

        userName.text = "${user?.lastName} ${user?.name}"

        cardView.setOnClickListener {
            if (listener != null) {
                listener!!.selectUser(usersList[position])
            }
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}