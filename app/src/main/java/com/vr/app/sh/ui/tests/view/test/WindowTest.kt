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
import com.vr.app.sh.data.repository.RoomDB
import com.vr.app.sh.ui.base.OpenTestViewModelFactory
import com.vr.app.sh.ui.tests.adapter.ActiveTestAdapter
import com.vr.app.sh.ui.tests.view.result.ResultTest
import com.vr.app.sh.ui.tests.viewmodel.OpenTestViewModel

class WindowTest : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: OpenTestViewModelFactory

    lateinit var viewModel:OpenTestViewModel




    var num_correct_otv = 0
    var num_error_otv = 0
    var num_not_otv = 0
    var test_id = 0
    lateinit var info_questions:Array<Int>

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
            info_questions = Array(it.size){ 3 }
            test_id = it[0].test_id
            viewPager.adapter = ActiveTestAdapter(supportFragmentManager,it,info_questions,tabLayout)
        }

    }

    private fun TestRes(){
        num_correct_otv = 0
        num_error_otv = 0
        num_not_otv = 0
        for (i in info_questions.size-1 downTo 0){
            when(info_questions[i]){
                1 -> num_correct_otv++
                2 -> num_error_otv++
                3 -> num_not_otv++
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_act_test,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_menu_res ->{
                TestRes()
                var intent = Intent(this,ResultTest::class.java)
                intent.putExtra("test_id",test_id)
                intent.putExtra("num_correct_otv",num_correct_otv)
                intent.putExtra("num_error_otv",num_error_otv)
                intent.putExtra("num_not_otv",num_not_otv)
                intent.putExtra("test_result",num_correct_otv*100/info_questions.size)
                startActivity(intent)
                finish()
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }
    }
}