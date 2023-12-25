package com.vr.app.sh.ui.tests.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R
import com.vr.app.sh.domain.model.Test

class BtnTestAdapter : RecyclerView.Adapter<BtnTestAdapter.ViewHolder>() {
    private var listener: Listener? = null
    var listTests:List<Test> = listOf()

    fun setTests(tests: List<Test>) {
        this.listTests = tests
        notifyDataSetChanged()
    }

    interface Listener {
        fun Clicked(name_test: String?,id_test:Int, index: Int)
    }

    override fun getItemCount(): Int = listTests.size

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_button_test, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView
        val textView = cardView.findViewById<View>(R.id.text_btn_test) as TextView
        textView.setText(listTests[position].name_test)
        val num_test = cardView.findViewById<View>(R.id.num_test) as TextView
        val num = position + 1
        num_test.text = num.toString()
        cardView.setOnClickListener {
            if (listener != null) {
                listener!!.Clicked(textView.text.toString(), listTests[position].id, position)
            }
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}