package com.vr.app.sh.ui.messages.chat.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.vr.app.sh.R
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.domain.model.messages.Message

class ChatViewAdapter(val context: Context,val userId:Int) : RecyclerView.Adapter<ChatViewAdapter.ViewHolder>() {
    private var listener: Listener? = null
    private var listMessages:List<Message> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setMessages(listMessages: List<Message>) {
        this.listMessages = listMessages
        notifyDataSetChanged()
    }

    interface Listener {
        fun select(message: Message)
    }

    override fun getItemCount(): Int = listMessages.size


    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message_type1, parent, false) as CardView
        return ViewHolder(cv)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView

        val layoutMyMessage = cardView.findViewById<ConstraintLayout>(R.id.layout_my_message)
        val layoutOtherMessage = cardView.findViewById<LinearLayout>(R.id.layout_other_message)

        if (listMessages[position].userToSendId==userId){
            layoutMyMessage.apply {
                visibility = View.VISIBLE
            }
            layoutOtherMessage.apply {
                visibility = View.GONE
            }
            val textMessage = cardView.findViewById<TextView>(R.id.textMyMessage)
            textMessage.text = listMessages[position].res

        }else{
            layoutMyMessage.apply {
                visibility = View.GONE
            }
            layoutOtherMessage.apply {
                visibility = View.VISIBLE
            }
            val textMessage = cardView.findViewById<TextView>(R.id.textOtherMessage)
            textMessage.text = listMessages[position].res

            val userPhoto = cardView.findViewById<ShapeableImageView>(R.id.user_photo)
            Glide.with(context)
                .load(NetworkService.BASE_URL + "/Photo?id=" + userId)
                .placeholder(R.drawable.bg_btn_send)
                .into(userPhoto)
        }

    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}