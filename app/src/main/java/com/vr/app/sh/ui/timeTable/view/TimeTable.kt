package com.vr.app.sh.ui.timeTable.view

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.data.model.Lesson
import com.vr.app.sh.ui.base.DayViewModelFactory
import com.vr.app.sh.ui.base.TimeTableViewModelFactory
import com.vr.app.sh.ui.timeTable.adapter.PagerTimeTableAdapter
import com.vr.app.sh.ui.timeTable.viewModel.TimeTableViewModel

class TimeTable : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: TimeTableViewModelFactory

    lateinit var viewModel: TimeTableViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table)

        (applicationContext as App).appComponent.injectTimeTable(this)

        viewModel = ViewModelProvider(this,factory)
            .get(TimeTableViewModel::class.java)

        val viewPager = findViewById<ViewPager2>(R.id.pager_windows_timeTable)
        viewPager.adapter = PagerTimeTableAdapter(this)

        val tabLayout = findViewById<TabLayout>(R.id.tabs_windows_timeTable)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position){
                0->tab.text = "ПН"
                1->tab.text = "ВТ"
                2->tab.text = "СР"
                3->tab.text = "ЧТ"
                4->tab.text = "ПТ"
            }
        }.attach()

        val fab = findViewById<FloatingActionButton>(R.id.FabTimeTable)
        fab.setOnClickListener {
            viewModel.openAlertAddLesson(this)
        }
    }
}