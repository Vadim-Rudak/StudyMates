package com.vr.app.sh.ui.tests.view.listTests

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vr.app.sh.R
import com.vr.app.sh.app.USER_ROLE
import com.vr.app.sh.ui.tests.adapter.PagerTestsAdapter
import com.vr.app.sh.ui.tests.view.addTest.AddTest

class WindowTestsNames : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_tests_names)

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
            val num_tab:Int = tabLayout.selectedTabPosition + 1
            val intent = Intent(this,AddTest::class.java)
            intent.putExtra("sub",subject)
            intent.putExtra("num_class", num_tab)
            startActivity(intent)

        }
    }
}