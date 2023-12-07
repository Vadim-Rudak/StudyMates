package com.vr.app.sh.ui.other.cityPicker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R
import com.vr.app.sh.data.repository.RegistrationInfo

class CityAdapter : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    private var listener: Listener? = null
    lateinit var itemList:Array<String>

    fun setItems(items: Array<String>) {
        this.itemList = items
        notifyDataSetChanged()
    }

    interface Listener {
        fun Click(citySelected:String)
    }

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    override fun getItemCount(): Int = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView
        val viewText = cardView.findViewById<TextView>(R.id.text_item)
        viewText.text = itemList[position]
        val viewCheck = cardView.findViewById<ImageView>(R.id.item_check)
        if (RegistrationInfo.user.cityLive == itemList[position]){
            viewCheck.setImageResource(R.drawable.ic_item_country_true)
        }else{
            viewCheck.setImageResource(R.color.white)
        }

        cardView.setOnClickListener {
            RegistrationInfo.user.cityLive = itemList[position]
            listener!!.Click(citySelected = itemList[position])
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}