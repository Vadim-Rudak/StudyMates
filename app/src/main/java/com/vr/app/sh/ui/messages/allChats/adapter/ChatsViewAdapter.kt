package com.vr.app.sh.ui.messages.allChats.adapter

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
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.domain.model.messages.UserInChat

class ChatsViewAdapter(val context: Context) : RecyclerView.Adapter<ChatsViewAdapter.ViewHolder>() {
    private var listener: Listener? = null
    private var listMyChats:List<UserInChat> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setMyChats(listUsers: List<UserInChat>) {
        listMyChats = listUsers
        notifyDataSetChanged()
    }

    interface Listener {
        fun click(userInChat: UserInChat)
    }

    override fun getItemCount(): Int = listMyChats.size


    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item_my_chat, parent, false) as CardView
        return ViewHolder(cv)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView

        val userPhoto = cardView.findViewById<ShapeableImageView>(R.id.userPhoto)
        val userName = cardView.findViewById<TextView>(R.id.textName)

        Glide.with(context)
            .load(NetworkService.BASE_URL + "/Photo?id=" + listMyChats[position].userId)
            .placeholder(R.drawable.bg_with_radius_12dp)
            .into(userPhoto)

        userName.text = "${listMyChats[position].user?.lastName} ${listMyChats[position].user?.name}"

        cardView.setOnClickListener {
            if (listener != null) {
                listener!!.click(listMyChats[position])
            }
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}