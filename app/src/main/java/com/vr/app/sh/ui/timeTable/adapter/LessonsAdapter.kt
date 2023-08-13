package com.vr.app.sh.ui.timeTable.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R
import com.vr.app.sh.data.model.Lesson
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
import kotlin.time.Duration

class LessonsAdapter : RecyclerView.Adapter<LessonsAdapter.ViewHolder>() {

    var listLessons:List<Lesson> = listOf()

    fun setLessons(lessons: List<Lesson>){
        this.listLessons = lessons
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_button_lesson, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun getItemCount(): Int {
        return listLessons.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView
        val viewTimeStart = cardView.findViewById<TextView>(R.id.btn_lesson_time_start)
        viewTimeStart.text = listLessons[position].timeStart
        val viewTimeEnd = cardView.findViewById<TextView>(R.id.btn_lesson_time_end)
        viewTimeEnd.text = listLessons[position].timeEnd
        val nameLesson = cardView.findViewById<TextView>(R.id.btn_lesson_name)
        nameLesson.text = listLessons[position].name
        val numClass = cardView.findViewById<TextView>(R.id.btn_lesson_num_class)
        numClass.text = listLessons[position].num_class
        val btnMore = cardView.findViewById<ImageButton>(R.id.btn_lesson_more)
        btnMore.setOnClickListener {

        }

    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

}
