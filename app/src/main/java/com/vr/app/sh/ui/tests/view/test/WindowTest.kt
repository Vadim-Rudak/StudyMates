package com.vr.app.sh.ui.tests.view.test

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
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

        setSupportActionBar(findViewById(R.id.toolbar_active_test))
        val actionBar = supportActionBar
        actionBar?.title = intent.extras?.getString("name_test")
        val viewPager = findViewById<ViewPager>(R.id.pager_questions)
        val tabLayout = findViewById<TabLayout>(R.id.tabs_active_test)
        tabLayout.setupWithViewPager(viewPager)

        (applicationContext as App).appComponent.injectWindowTest(this)

        viewModel = ViewModelProvider(this,factory)
            .get(OpenTestViewModel::class.java)

        viewModel.listQuestions.observe(this){
            viewModel.setInfoQuestions(it)
            viewPager.adapter = ActiveTestAdapter(supportFragmentManager,it,viewModel.arrayAnswers,tabLayout)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_act_test,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_menu_res ->{
                val intent = Intent(this,TestResultAct::class.java)
                intent.putExtra("objTestResult",viewModel.getTestResult())
                startActivity(intent)
                finish()
                true
            }

            else ->{
                super.onOptionsItemSelected(item)
            }
        }
    }
}