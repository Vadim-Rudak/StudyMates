package com.vr.app.sh.ui.tests.view.addTest

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.ui.base.AddTestViewModelFactory
import com.vr.app.sh.ui.other.UseAlert
import com.vr.app.sh.ui.tests.viewmodel.AddTestViewModel
import kotlinx.coroutines.*

class AddQuestion : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: AddTestViewModelFactory

    lateinit var viewModel: AddTestViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)

        (applicationContext as App).appComponent.injectAddQuestion(this)

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        viewTitle.text = resources.getString(R.string.window_add_question_title)

        val TextQuestion = findViewById<EditText>(R.id.InputQuestion)
        val TextOtv1 = findViewById<EditText>(R.id.InputOtv1)
        val TextOtv2 = findViewById<EditText>(R.id.InputOtv2)
        val TextOtv3 = findViewById<EditText>(R.id.InputOtv3)
        val TextOtv4 = findViewById<EditText>(R.id.InputOtv4)
        val TextCorrectOtv = findViewById<EditText>(R.id.InputCorrectOtv)
        val AddNextQuestion = findViewById<Button>(R.id.AddNextQuestion)
        val progressBar = findViewById<CircularProgressBar>(R.id.progressBarAddTest)
        progressBar.apply {
            progressBarWidth = 14f
            backgroundProgressBarWidth = 2f
            backgroundProgressBarColor = Color.parseColor("#969696")
            progressBarColorStart = Color.parseColor("#6767bb")
            progressBarColorEnd = Color.parseColor("#4c7daf")
            roundBorder = true
            indeterminateMode = true
        }
        val textProgress = findViewById<TextView>(R.id.textProgressAddTest)
        val AddInServer = findViewById<Button>(R.id.BtnSave)

        viewModel = ViewModelProvider(this, factory)
            .get(AddTestViewModel::class.java)

        viewModel.errorMessage.observe(this){
            UseAlert.errorMessage(it,this)
        }

        viewModel.visibleProgressBar.observe(this){
            if (it){
                TextQuestion.visibility = View.GONE
                TextOtv1.visibility = View.GONE
                TextOtv2.visibility = View.GONE
                TextOtv3.visibility = View.GONE
                TextOtv4.visibility = View.GONE
                TextCorrectOtv.visibility = View.GONE
                AddNextQuestion.visibility = View.GONE
                AddInServer.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                textProgress.visibility = View.VISIBLE
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main) {
                        progressBar.visibility = View.GONE
                        textProgress.text = resources.getString(R.string.test_added)
                        delay(1000)
                        finish()
                    }
                }
            }
        }

        AddNextQuestion.setOnClickListener {
            if(!TextUtils.isEmpty(TextQuestion.text.toString().trim())&&!TextUtils.isEmpty(TextOtv1.text.toString().trim())&&
                    !TextUtils.isEmpty(TextOtv2.text.toString().trim())&&!TextUtils.isEmpty(TextOtv3.text.toString().trim())&&
                    !TextUtils.isEmpty(TextOtv4.text.toString().trim())&&!TextUtils.isEmpty(TextCorrectOtv.text.toString().trim())){
                viewModel.listQuestions.add(
                    Question(0,TextQuestion.text.toString(), TextOtv1.text.toString(), TextOtv2.text.toString(),
                    TextOtv3.text.toString(), TextOtv4.text.toString(), TextCorrectOtv.text.toString())
                )
                TextQuestion.setText("")
                TextOtv1.setText("")
                TextOtv2.setText("")
                TextOtv3.setText("")
                TextOtv4.setText("")
                TextCorrectOtv.setText("")
                AddNextQuestion.text = resources.getString(R.string.window_add_question_btn_add_next)
                AddInServer.visibility = View.VISIBLE
            }else{
                if (TextUtils.isEmpty(TextQuestion.text.toString().trim())){
                    TextQuestion.setText("")
                    TextQuestion.hint = resources.getString(R.string.window_add_question_inp1)
                }
                if (TextUtils.isEmpty(TextOtv1.text.toString().trim())){
                    TextOtv1.setText("")
                    TextOtv1.hint = resources.getString(R.string.window_add_question_inp2)
                }
                if (TextUtils.isEmpty(TextOtv2.text.toString().trim())){
                    TextOtv2.setText("")
                    TextOtv2.hint = resources.getString(R.string.window_add_question_inp3)
                }
                if (TextUtils.isEmpty(TextOtv3.text.toString().trim())){
                    TextOtv3.setText("")
                    TextOtv3.hint = resources.getString(R.string.window_add_question_inp4)
                }
                if (TextUtils.isEmpty(TextOtv4.text.toString().trim())){
                    TextOtv4.setText("")
                    TextOtv4.hint = resources.getString(R.string.window_add_question_inp5)
                }
                if (TextUtils.isEmpty(TextCorrectOtv.text.toString().trim())){
                    TextCorrectOtv.setText("")
                    TextCorrectOtv.hint = resources.getString(R.string.window_add_question_inp6)
                }
            }
        }
        AddInServer.setOnClickListener {
            if (viewModel.listQuestions.size!=0){
                viewModel.sendTestWithQuestions(intent.extras?.getString("subject").toString(),
                    intent.extras?.getInt("num_class")!!,intent.extras?.getString("name_test")!!)
            }else{
                viewModel.errorMessage.value = resources.getString(R.string.window_add_question_error)
            }
        }
    }
}