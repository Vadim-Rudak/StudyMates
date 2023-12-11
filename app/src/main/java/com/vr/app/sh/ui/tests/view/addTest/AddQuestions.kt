package com.vr.app.sh.ui.tests.view.addTest

import android.os.Bundle
import android.view.Gravity
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.AddTestViewModelFactory
import com.vr.app.sh.ui.other.UseAlert.Companion.errorMessage
import com.vr.app.sh.ui.tests.adapter.QuestionItemDecoration
import com.vr.app.sh.ui.tests.viewmodel.AddTestViewModel

class AddQuestions : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: AddTestViewModelFactory

    lateinit var viewModel: AddTestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)

        (applicationContext as App).appComponent.injectAddQuestion(this)
        viewModel = ViewModelProvider(this, factory)
            .get(AddTestViewModel::class.java)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val textTitle = findViewById<TextView>(R.id.title_add_test)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val headerView = navigationView.getHeaderView(0)

        val btnSeeList = findViewById<ImageButton>(R.id.btn_see_list)
        btnSeeList.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        val btnSendTest = findViewById<ImageButton>(R.id.btnSend)
        btnSendTest.setOnClickListener {
            viewModel.sendTest()
        }

        val recyclerQuestions = headerView.findViewById<RecyclerView>(R.id.list_select_question)
        recyclerQuestions.layoutManager = LinearLayoutManager(this)
        recyclerQuestions.adapter = viewModel.adapter
        recyclerQuestions.addItemDecoration(QuestionItemDecoration(resources))

        val btnAddQuestion = headerView.findViewById<ImageButton>(R.id.btnAddQuestion)
        btnAddQuestion.setOnClickListener {
            viewModel.selectQuestionToAdd(supportFragmentManager)
        }

        viewModel.errorMessage.observe(this){
            errorMessage(it,this)
        }

        viewModel.textTitle.observe(this){
            textTitle.text = it
        }

        viewModel.addTypeQuestion.observe(this){
            viewModel.addQuestion(it)
        }

        viewModel.selectQuestion.observe(this){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fr_place, it)
                .commit()
        }

//        val TextQuestion = findViewById<EditText>(R.id.InputQuestion)
//        val TextOtv1 = findViewById<EditText>(R.id.InputOtv1)
//        val TextOtv2 = findViewById<EditText>(R.id.InputOtv2)
//        val TextOtv3 = findViewById<EditText>(R.id.InputOtv3)
//        val TextOtv4 = findViewById<EditText>(R.id.InputOtv4)
//        val TextCorrectOtv = findViewById<EditText>(R.id.InputCorrectOtv)
//        val AddNextQuestion = findViewById<Button>(R.id.AddNextQuestion)
//        val progressBar = findViewById<CircularProgressBar>(R.id.progressBarAddTest)
//        progressBar.apply {
//            progressBarWidth = 14f
//            backgroundProgressBarWidth = 2f
//            backgroundProgressBarColor = Color.parseColor("#969696")
//            progressBarColorStart = Color.parseColor("#6767bb")
//            progressBarColorEnd = Color.parseColor("#4c7daf")
//            roundBorder = true
//            indeterminateMode = true
//        }
//        val textProgress = findViewById<TextView>(R.id.textProgressAddTest)
//        val AddInServer = findViewById<Button>(R.id.BtnSave)
//
//
//        viewModel = ViewModelProvider(this, factory)
//            .get(AddTestViewModel::class.java)
//
//        viewModel.errorMessage.observe(this){
//            UseAlert.errorMessage(it,this)
//        }
//
//        viewModel.vizibleProgressBar.observe(this){
//            if (it){
//                TextQuestion.visibility = View.GONE
//                TextOtv1.visibility = View.GONE
//                TextOtv2.visibility = View.GONE
//                TextOtv3.visibility = View.GONE
//                TextOtv4.visibility = View.GONE
//                TextCorrectOtv.visibility = View.GONE
//                AddNextQuestion.visibility = View.GONE
//                AddInServer.visibility = View.GONE
//                progressBar.visibility = View.VISIBLE
//                textProgress.visibility = View.VISIBLE
//            }else{
//                CoroutineScope(Dispatchers.IO).launch {
//                    withContext(Dispatchers.Main) {
//                        progressBar.visibility = View.GONE
//                        textProgress.setText("Тест успешно добавлен")
//                        delay(1000)
//                        finish()
//                    }
//                }
//            }
//        }
//
//        AddNextQuestion.setOnClickListener {
//            if(!TextUtils.isEmpty(TextQuestion.text.toString().trim())&&!TextUtils.isEmpty(TextOtv1.text.toString().trim())&&
//                    !TextUtils.isEmpty(TextOtv2.text.toString().trim())&&!TextUtils.isEmpty(TextOtv3.text.toString().trim())&&
//                    !TextUtils.isEmpty(TextOtv4.text.toString().trim())&&!TextUtils.isEmpty(TextCorrectOtv.text.toString().trim())){
//                viewModel.listQuestions.add(
//                    Question(0,TextQuestion.text.toString(), TextOtv1.text.toString(), TextOtv2.text.toString(),
//                    TextOtv3.text.toString(), TextOtv4.text.toString(), TextCorrectOtv.text.toString())
//                )
//                TextQuestion.setText("")
//                TextOtv1.setText("")
//                TextOtv2.setText("")
//                TextOtv3.setText("")
//                TextOtv4.setText("")
//                TextCorrectOtv.setText("")
//                AddNextQuestion.setText("Добавить ещё вопрос")
//                AddInServer.visibility = View.VISIBLE
//            }else{
//                if (TextUtils.isEmpty(TextQuestion.text.toString().trim())){
//                    TextQuestion.setText("")
//                    TextQuestion.hint = "Добавьте вопрос"
//                }
//                if (TextUtils.isEmpty(TextOtv1.text.toString().trim())){
//                    TextOtv1.setText("")
//                    TextOtv1.hint = "Добавьте ответ №1"
//                }
//                if (TextUtils.isEmpty(TextOtv2.text.toString().trim())){
//                    TextOtv2.setText("")
//                    TextOtv2.hint = "Добавьте ответ №2"
//                }
//                if (TextUtils.isEmpty(TextOtv3.text.toString().trim())){
//                    TextOtv3.setText("")
//                    TextOtv3.hint = "Добавьте ответ №3"
//                }
//                if (TextUtils.isEmpty(TextOtv4.text.toString().trim())){
//                    TextOtv4.setText("")
//                    TextOtv4.hint = "Добавьте ответ №4"
//                }
//                if (TextUtils.isEmpty(TextCorrectOtv.text.toString().trim())){
//                    TextCorrectOtv.setText("")
//                    TextCorrectOtv.hint = "Добавьте правильный ответ"
//                }
//            }
//        }
//        AddInServer.setOnClickListener {
//            if (viewModel.listQuestions.size!=0){
//                viewModel.sendTestWithQuestions(intent.extras?.getString("subject").toString(),
//                    intent.extras?.getInt("num_class")!!,intent.extras?.getString("name_test")!!)
//            }else{
//                viewModel.errorMessage.value = "Пустой список вопросов"
//            }
//        }
    }
}