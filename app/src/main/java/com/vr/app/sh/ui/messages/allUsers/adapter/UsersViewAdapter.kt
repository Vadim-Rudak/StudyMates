package com.vr.app.sh.ui.messages.allUsers.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.vr.app.sh.R
import com.vr.app.sh.data.api.NetworkService.Companion.BASE_URL
import com.vr.app.sh.domain.model.User

class UsersViewAdapter(val context: Context) : RecyclerView.Adapter<UsersViewAdapter.ViewHolder>() {
    private var listener: Listener? = null
    private var usersList:List<User> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(users: List<User>) {
        this.usersList = users
        notifyDataSetChanged()
    }

    interface Listener {
        fun selectUser(user: User)
    }

    override fun getItemCount(): Int = usersList.size


    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_button_user, parent, false) as CardView
        return ViewHolder(cv)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView

        val userPhoto = cardView.findViewById<ShapeableImageView>(R.id.userPhoto)
        val userName = cardView.findViewById<TextView>(R.id.textName)

        Glide.with(context)
            .load(BASE_URL + "/Photo?id=" + usersList[position].id)
            .placeholder(R.drawable.bg_with_radius_12dp)
            .into(userPhoto)

        userName.text = "${usersList[position].lastName} ${usersList[position].name}"

        cardView.setOnClickListener {
            if (listener != null) {
                listener!!.selectUser(usersList[position])
            }
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}