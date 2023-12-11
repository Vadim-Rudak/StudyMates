package com.vr.app.sh.ui.tests.adapter

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R
import com.vr.app.sh.domain.model.questions.Question

class BtnSelectQuestionAdapter(private var listQuestions:ArrayList<Question>,private val resources: Resources) : RecyclerView.Adapter<BtnSelectQuestionAdapter.ViewHolder>() {

    private var listener: Listener? = null

    fun setQuestions(list: ArrayList<Question>) {
        listQuestions = list
        notifyDataSetChanged()
    }

    interface Listener {
        fun click(question: Question,num_pos:Int)
    }

    override fun getItemCount(): Int = listQuestions.size

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_select_question, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView

        val name = cardView.findViewById<TextView>(R.id.textSelectQuestion)
        name.text = "${resources.getString(R.string.window_add_questions_item)} ${position + 1}"
        val btnDrop = cardView.findViewById<ImageButton>(R.id.dropQuestion)
        btnDrop.setOnClickListener {
            listQuestions.removeAt(position)
            notifyDataSetChanged()
        }

        cardView.setOnClickListener {
            if (listener != null) {
                listener!!.click(listQuestions[position],position)
            }
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}