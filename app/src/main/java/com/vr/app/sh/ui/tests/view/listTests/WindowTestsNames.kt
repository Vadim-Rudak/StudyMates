package com.vr.app.sh.ui.tests.view.listTests

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vr.app.sh.R
import com.vr.app.sh.app.USER_ROLE
import com.vr.app.sh.ui.other.UseAlert.Companion.writeNameTest
import com.vr.app.sh.ui.tests.adapter.PagerTestsAdapter
import com.vr.app.sh.ui.tests.view.addTest.AddQuestion

class WindowTestsNames : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_tests_names)

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        viewTitle.text = resources.getString(R.string.TopMenu_btn2)

        val viewPager = findViewById<ViewPager2>(R.id.pager_questions)
        viewPager.adapter = PagerTestsAdapter(this)

        val tabLayout = findViewById<TabLayout>(R.id.tabs_active_test)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "${(position + 1)} КЛАСС"
        }.attach()

        val subject = intent.extras?.getString("sub")
        val addTest = findViewById<FloatingActionButton>(R.id.AddTest)
        if (USER_ROLE == "ADMIN"){
            addTest.visibility = View.VISIBLE
        }else{
            addTest.visibility = View.GONE
        }
        addTest.setOnClickListener {
            val intent = Intent(this,AddQuestion::class.java).apply {
                putExtra("subject",subject)
                putExtra("num_class", tabLayout.selectedTabPosition + 1)
            }
            writeNameTest(intent).show(supportFragmentManager,"alertAddTest")
        }
    }
}