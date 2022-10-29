package com.vr.app.sh.ui.tests.view.result

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.ResultViewModelFactory
import com.vr.app.sh.ui.tests.viewmodel.ResultViewModel

class ResultTest : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: ResultViewModelFactory

    lateinit var viewModel:ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_test)

        val text_correct_otv = findViewById<TextView>(R.id.res_text_correct)
        text_correct_otv.text = intent.extras?.getInt("num_correct_otv").toString()
        val text_error_otv = findViewById<TextView>(R.id.res_text_err)
        text_error_otv.text = intent.extras?.getInt("num_error_otv").toString()
        val text_not_otv = findViewById<TextView>(R.id.res_text_n)
        text_not_otv.text = intent.extras?.getInt("num_not_otv").toString()
        val text_all_res = findViewById<TextView>(R.id.TextAllResult)
        text_all_res.text = intent.extras?.getInt("test_result").toString()

        (applicationContext as App).appComponent.injectResultTest(this)

        viewModel = ViewModelProvider(this,factory)
            .get(ResultViewModel::class.java)

        viewModel.status_send.observe(this){
            if (it){
                finish()
            }
        }

        viewModel.errorMessage.observe(this){
            viewModel.errorMessage(it,this)
        }

        val btn_send_result = findViewById<Button>(R.id.save_res_btn)
        btn_send_result.setOnClickListener {
            viewModel.sendResult(intent.extras?.getInt("test_id")!!,intent.extras?.getInt("test_result")!!,
                intent.extras?.getInt("num_correct_otv")!!,intent.extras?.getInt("num_error_otv")!!,
                intent.extras?.getInt("num_not_otv")!!)
        }
    }
}