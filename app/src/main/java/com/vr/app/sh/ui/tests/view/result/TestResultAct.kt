package com.vr.app.sh.ui.tests.view.result

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.domain.model.ResultTest
import com.vr.app.sh.ui.base.ResultViewModelFactory
import com.vr.app.sh.ui.other.UseAlert
import com.vr.app.sh.ui.tests.viewmodel.ResultViewModel

class TestResultAct : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: ResultViewModelFactory

    lateinit var viewModel:ResultViewModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_test)

        val testResult = intent.getSerializableExtra("objTestResult",ResultTest::class.java)

        val viewCorrectAnswer = findViewById<TextView>(R.id.res_text_correct)
        val viewWrongAnswer = findViewById<TextView>(R.id.res_text_err)
        val viewNotAnswer = findViewById<TextView>(R.id.res_text_n)
        val viewAllResult = findViewById<TextView>(R.id.textAllResult)
        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        viewTitle.text = resources.getString(R.string.toolbar_result)

        if (testResult!=null){
            viewCorrectAnswer.text = testResult.num_correct_answer.toString()
            viewWrongAnswer.text = testResult.num_wrong_answer.toString()
            viewNotAnswer.text = testResult.num_not_answer.toString()
            viewAllResult.text = testResult.all_result.toString()
        }

        (applicationContext as App).appComponent.injectResultTest(this)

        viewModel = ViewModelProvider(this,factory)
            .get(ResultViewModel::class.java)

        viewModel.status_send.observe(this){
            if (it){
                finish()
            }
        }

        viewModel.errorMessage.observe(this){
            UseAlert.errorMessage(it,this)
        }

        val btnSendResult = findViewById<Button>(R.id.save_res_btn)
        btnSendResult.setOnClickListener {
            if (testResult!=null){
                viewModel.sendResult(testResult)
            }
        }
    }
}