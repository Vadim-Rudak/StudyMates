package com.vr.app.sh.ui.menu.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R

class TopMenuAdapter(resources: Resources) : RecyclerView.Adapter<TopMenuAdapter.ViewHolder>() {

    private var listener: Listener? = null
    private var listView = resources.obtainTypedArray(R.array.topMenuViewBtn)
    private var listFun = resources.getStringArray(R.array.nameTopFun)

    interface Listener{
        fun onClick(position:Int)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_top_menu, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun getItemCount(): Int = listFun.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView

        val image = cardView.findViewById<ImageView>(R.id.imgBtnTopMenu)
        image.setImageDrawable(listView.getDrawable(position))
        val name = cardView.findViewById<TextView>(R.id.txtBtnTopMenu)
        name.text = listFun[position]

        cardView.setOnClickListener {
            listener!!.onClick(position)
        }

    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

}