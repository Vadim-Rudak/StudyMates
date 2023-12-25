package com.vr.app.sh.ui.timeTable.viewModel

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.vr.app.sh.R
import com.vr.app.sh.domain.model.Lesson
import com.vr.app.sh.domain.UseCase.SaveLessonInBD
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class TimeTableViewModel(val saveLessonInBD: SaveLessonInBD):ViewModel() {

    var job: Job? = null


    fun openAlertAddLesson(context: Context){
        val objLesson = Lesson()
        val altAddLesson = MaterialAlertDialogBuilder(context)
        altAddLesson.setTitle(context.resources.getString(R.string.alrAddLessonTitel))
        altAddLesson.setMessage(context.resources.getString(R.string.alrAddLessonMoreInfo))

        val inflater = LayoutInflater.from(context)
        val viewAlert: View = inflater.inflate(R.layout.alert_add_lesson, null)
        altAddLesson.setView(viewAlert)

        val selectDay = viewAlert.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        val nameDays = context.resources.getStringArray(R.array.numDay)
        val arrayAdapter = ArrayAdapter(context, R.layout.day_item,nameDays)
        selectDay.setAdapter(arrayAdapter)
        selectDay.setOnItemClickListener { parent, view, position, id ->
            objLesson.num_day = position
        }

        val viewNameLesson = viewAlert.findViewById<TextInputEditText>(R.id.alrAddLessonName)
        val viewNumClass = viewAlert.findViewById<TextInputEditText>(R.id.alrAddLessonNumClass)
        val selectStartTime = viewAlert.findViewById<TextInputEditText>(R.id.alrAddLessonSelectTime)
        selectStartTime.setOnClickListener {
            openTimePicker(context = context,
                onClick = { timeStart,timeEnd->

                selectStartTime.setText(timeStart)
                objLesson.timeStart = timeStart
                    objLesson.timeEnd = timeEnd
            })
        }

        altAddLesson.setNegativeButton(context.getString(R.string.alrBtnCansel),DialogInterface.OnClickListener{ dialogInterface, i ->
            dialogInterface.dismiss()
        })

        altAddLesson.setPositiveButton(context.getString(R.string.alrBtnOk), DialogInterface.OnClickListener { dialogInterface, i ->
            objLesson.name = viewNameLesson.text.toString()
            objLesson.num_class = viewNumClass.text.toString()

            job = CoroutineScope(Dispatchers.IO).launch {
                saveLessonInBD.execute(objLesson)
            }
            dialogInterface.dismiss()
        })
        altAddLesson.show()
    }



    private fun openTimePicker(context: Context, onClick:(String,String) -> Unit){
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(8)
            .setMinute(0)
            .setTitleText(context.resources.getString(R.string.alrAddLessonEt3))
            .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
            .build()
        picker.addOnPositiveButtonClickListener {
            val timeStart = LocalTime.of(picker.hour, picker.minute)
            val timeEnd = timeStart.plusMinutes(45)
            onClick(
                timeStart.format(DateTimeFormatter.ofPattern("HH:mm")),
                timeEnd.format(DateTimeFormatter.ofPattern("HH:mm"))
            )
        }
        picker.show((context as AppCompatActivity).supportFragmentManager,"t")
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}