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
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.model.messages.FavoriteUser

class SelectedUsersViewAdapter(val context: Context) : RecyclerView.Adapter<SelectedUsersViewAdapter.ViewHolder>() {
    private var listener: Listener? = null
    private var listUsers:List<FavoriteUser> = listOf()

    companion object {
        private const val TYPE_USER = 0
        private const val TYPE_ADD = 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(listUsers: List<FavoriteUser>) {
        this.listUsers = listUsers
        notifyDataSetChanged()
    }

    interface Listener {
        fun click(user:User)
        fun selectUser()
    }

    override fun getItemCount() = listUsers.size + 1


    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    override fun getItemViewType(position: Int) = if (position==0) TYPE_ADD else TYPE_USER

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutRes = if (viewType==1) R.layout.card_item_add_user else R.layout.card_item_select_user

        val cv = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false) as CardView
        return ViewHolder(cv)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView

        if (position!=0){
            val userPhoto = cardView.findViewById<ShapeableImageView>(R.id.userPhoto)
            val userName = cardView.findViewById<TextView>(R.id.textName)

            userName.text = listUsers[position-1].user?.name

            Glide.with(context)
                .load(NetworkService.BASE_URL + "/Photo?id=" + (listUsers[position-1].user?.id ?: 0))
                .placeholder(R.drawable.bg_with_radius_12dp)
                .into(userPhoto)
        }

        cardView.setOnClickListener {
            if (listener != null) {
                if (position==0){
                    listener!!.selectUser()
                }else{
                    listener!!.click(listUsers[position-1].user!!)
                }
            }
        }

    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}