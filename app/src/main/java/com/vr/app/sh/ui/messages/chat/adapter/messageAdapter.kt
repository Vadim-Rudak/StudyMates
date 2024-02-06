package com.vr.app.sh.ui.messages.chat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R


//class messageAdapter(val context:Context): RecyclerView.Adapter<messageAdapter.ViewHolder>() {
//    private var listener: Listener? = null
////    var itemList:ArrayList<RoomMessage> = arrayListOf()
//
//    /*
//*TYPE MESSAGES
//*
//* if 1 => text message
//* if 2 => image message
//* if 3 => text + image message
//* if 4 => voice message
//*
//* */
//
//    companion object {
//        private const val TYPE_TEXT = 1
//        private const val TYPE_IMAGE = 2
//        private const val TYPE_TEXT_AND_IMAGE = 3
//        private const val TYPE_VOICE = 4
//    }
//
//    fun setItems(listAllMessages: List<RoomMessage>) {
//        itemList.clear()
//        for (message in listAllMessages ){
//            itemList.add(message)
//        }
//        notifyDataSetChanged()
//    }
//
//    fun addNewMessage(message: RoomMessage){
//        itemList.add(message)
//        notifyDataSetChanged()
//    }
//
//    interface Listener {
//        fun Clicked(id_item: Int)
//    }
//
//    override fun getItemCount(): Int = itemList.size
//
//    override fun getItemViewType(position: Int): Int {
//        return itemList[position].type_message
//    }
//
//    fun setListener(listener: Listener?) {
//        this.listener = listener
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return when (viewType) {
//            TYPE_TEXT -> { ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_my_message_type1, parent, false) as CardView) }
//            TYPE_IMAGE -> { ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_my_message_type1, parent, false) as CardView) }
//            TYPE_TEXT_AND_IMAGE -> { ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_my_message_type1, parent, false) as CardView) }
//            TYPE_VOICE -> { ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_my_message_type1, parent, false) as CardView) }
//            else -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_my_message_type1, parent, false) as CardView)
//        }
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val cardView = holder.cardView
//
//        when(itemList[position].type_message){
//            1->{
//                //добавить вместо 10 id текущего пользователя
//                val layout_my_message = cardView.findViewById<ConstraintLayout>(R.id.layout_my_message)
//                val layout_other_message = cardView.findViewById<ConstraintLayout>(R.id.layout_other_message)
//                if(itemList[position].id_user_send == USE_USER_ID){
//                    layout_my_message.visibility = View.VISIBLE
//                    layout_other_message.visibility = View.GONE
//                    val text_message = cardView.findViewById<TextView>(R.id.textMyMessage)
//                    val statusRead = cardView.findViewById<ConstraintLayout>(R.id.card_my_message_status_read)
//                    val time_send_message = cardView.findViewById<TextView>(R.id.card_my_message_text_time_send)
//
//                    text_message.text = itemList[position].info_message
//                    statusRead.visibility = View.GONE
//                    time_send_message.text = itemList[position].time_send
//                }else{
//                    layout_my_message.visibility = View.GONE
//                    layout_other_message.visibility = View.VISIBLE
//                    val text_message = cardView.findViewById<TextView>(R.id.textOtherMessage)
//                    val time_send_message = cardView.findViewById<TextView>(R.id.card_other_message_text_time_send)
//
//                    text_message.text = itemList[position].info_message
//                    time_send_message.text = itemList[position].time_send
//                }
//            }
//            2->{}
//            3->{}
//            4->{}
//        }
//
//    }
//
//    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
//}