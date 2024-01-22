package com.vr.app.sh.ui.tests.view.test

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.OpenTestViewModelFactory
import com.vr.app.sh.ui.tests.adapter.ActiveTestAdapter
import com.vr.app.sh.ui.tests.view.result.TestResultAct
import com.vr.app.sh.ui.tests.viewmodel.OpenTestViewModel

class WindowTest : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: OpenTestViewModelFactory
    lateinit var viewModel:OpenTestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_test)

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val btnDoneTest = findViewById<TextView>(R.id.click_done_test)
        btnDoneTest.setOnClickListener{
            val intent = Intent(this,TestResultAct::class.java)
            intent.putExtra("objTestResult",viewModel.getTestResult())
            startActivity(intent)
            finish()
        }
        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        viewTitle.text = intent.extras?.getString("name_test")
        val viewPager2 = findViewById<ViewPager2>(R.id.pager_questions)
        val tabLayout = findViewById<TabLayout>(R.id.tabs_active_test)

        (applicationContext as App).appComponent.injectWindowTest(this)

        viewModel = ViewModelProvider(this,factory)
            .get(OpenTestViewModel::class.java)

        viewModel.listQuestions.observe(this){
            viewModel.setInfoQuestions(it)
            viewPager2.adapter = ActiveTestAdapter(this,it,viewModel.arrayAnswers,tabLayout)
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = resources.getString(R.string.tab_test_question) + " ${position + 1}"
            }.attach()
        }
    }
}