package com.vr.app.sh.ui.tests.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R

class BtnSubjectAdapter(resources:Resources) : RecyclerView.Adapter<BtnSubjectAdapter.ViewHolder>() {

    private var listener: Listener? = null
    private var listSubjects = resources.getStringArray(R.array.list_subjects)
    private var listIcon = resources.obtainTypedArray(R.array.list_subjects_ic)

    interface Listener {
        fun click(name_subject: String)
    }

    override fun getItemCount(): Int = listSubjects.size

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_subject, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView

        val text = cardView.findViewById<TextView>(R.id.textInCard)
        text.text = listSubjects[position]
        val image = cardView.findViewById<ImageView>(R.id.imgBtnSubject)
        image.setImageDrawable(listIcon.getDrawable(position))

        cardView.setOnClickListener {
            if (listener != null) {
                listener!!.click(listSubjects[position])
            }
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}