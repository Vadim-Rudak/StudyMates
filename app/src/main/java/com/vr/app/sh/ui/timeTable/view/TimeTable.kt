package com.vr.app.sh.ui.timeTable.view

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vr.app.sh.R
import com.vr.app.sh.app.App
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
        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        viewTitle.text = resources.getString(R.string.TopMenu_btn3)

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