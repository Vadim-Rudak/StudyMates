package com.vr.app.sh.ui.tests.view.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.R
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.data.repository.UserRepoImpl
import com.vr.app.sh.domain.UseCase.GetUserBD
import com.vr.app.sh.domain.repository.UserRepo
import com.vr.app.sh.ui.base.ResultViewModelFactory
import com.vr.app.sh.ui.base.TestsOneClassViewModelFactory
import com.vr.app.sh.ui.tests.viewmodel.ResultViewModel
import com.vr.app.sh.ui.tests.viewmodel.TestsOneClassViewModel
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class ResultTest : AppCompatActivity() {

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

        val retrofitService = NetworkService.getInstance()
        val mainRepository = InternetRepoImpl(retrofitService)
        val userRepo:UserRepo = UserRepoImpl(this)
        viewModel = ViewModelProvider(this, ResultViewModelFactory(mainRepository,userRepo,this))
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