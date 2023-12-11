package com.vr.app.sh.ui.tests.viewmodel

import android.content.res.Resources
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.GetListTestsInternet
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveTestsInBD
import com.vr.app.sh.domain.UseCase.SendQuestions
import com.vr.app.sh.domain.UseCase.SendTestInfo
import com.vr.app.sh.domain.model.questions.Question
import com.vr.app.sh.domain.model.questions.QuestionWithAnswers
import com.vr.app.sh.domain.model.questions.QuestionWithImg
import com.vr.app.sh.domain.model.questions.TypeQuestion
import com.vr.app.sh.ui.other.UseAlert.Companion.selectAddQuestion
import com.vr.app.sh.ui.tests.adapter.BtnSelectQuestionAdapter
import com.vr.app.sh.ui.tests.view.addTest.FillingQuestion

class AddTestViewModel(
    private val resources: Resources,
    val getListTestsInternet: GetListTestsInternet,
    val saveTestsInBD: SaveTestsInBD,
    val sendTestInfo: SendTestInfo,
    val sendQuestions: SendQuestions,
    val internetConnection: InternetConnection
    ): ViewModel() {

    val listQuestions = ArrayList<Question>()
    val adapter=BtnSelectQuestionAdapter(listQuestions,resources)
    val selectQuestion = MutableLiveData<FillingQuestion>()
    val addTypeQuestion = MutableLiveData<TypeQuestion>()
    val textTitle = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
//    var job: Job? = null

    init {
        adapter.setListener(object :BtnSelectQuestionAdapter.Listener{
            override fun click(question: Question, num_pos: Int) {
                textTitle.postValue("${resources.getString(R.string.window_add_questions_title)} ${num_pos + 1}")
                selectQuestion.postValue(FillingQuestion(question))
            }
        })
    }
    fun selectQuestionToAdd(fragmentManager: FragmentManager){
        selectAddQuestion(fragmentManager,addTypeQuestion)
    }

    fun addQuestion(typeQuestion: TypeQuestion){

        when(typeQuestion){
            TypeQuestion.Write ->{
                listQuestions.add(Question().apply {
                    type = typeQuestion
                })
            }
            TypeQuestion.Img ->{
                listQuestions.add(QuestionWithImg().apply {
                    type = typeQuestion
                })
            }
            else ->{
                listQuestions.add(QuestionWithAnswers().apply {
                    type = typeQuestion
                })
            }
        }
        adapter.notifyDataSetChanged()
    }

    fun sendTest(){
        var statusToSend = true
        var item:Question
        for (i in 0 until listQuestions.size){
            item = listQuestions[i]
            when(item){
                is QuestionWithAnswers ->{
                    if(
                        item.question==null||
                        item.otv1==null||
                        item.otv2==null||
                        item.otv3==null||
                        item.otv4==null||
                        item.correctAnswer==null){
                        statusToSend = false
                        errorMessage.postValue("Заполните все поля в вопросе ${i+1}")
                        break
                    }
                }
                is QuestionWithImg ->{

                }
                else->{

                }
            }
        }
    }

//    var listQuestions:ArrayList<Question> = ArrayList()
//    val errorMessage = MutableLiveData<String>()
//    val vizibleProgressBar = MutableLiveData<Boolean>()
//    var job: Job? = null
//
//    fun sendTestWithQuestions(name_subject:String,num_class:Int,name_test: String){
//        if (internetConnection.UseInternet()){
//            vizibleProgressBar.value = true
//            job = CoroutineScope(Dispatchers.IO).launch {
//                val response = sendTestInfo.execute(getJSONTest(name_subject,num_class, name_test,listQuestions.size))
//                val gs = Gson()
//                val jsonQuestions = gs.toJson(listQuestions)
//                val resp = sendQuestions.execute(jsonQuestions.toRequestBody("application/json".toMediaTypeOrNull()))
//                val resp2 = getListTestsInternet.execute(name_subject)
//
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful&&resp.isSuccessful) {
//                        if (resp2.isSuccessful){
//                            saveTestsInBD.execute(resp2.body()!!)
//                            vizibleProgressBar.value = false
//                        }
//                    } else {
//                        vizibleProgressBar.value = false
//                        errorMessage.value = resources.getString(R.string.alrErrorSendTest)
//                    }
//                }
//            }
//        }else{
//            errorMessage.value = resources.getString(R.string.alrNotInternetConnection)
//        }
//    }
//
//    fun getJSONTest(subject:String, num_class: Int?, name_test:String, num_questions:Int): RequestBody {
//        val jsonObject = JSONObject()
//        jsonObject.put("subject", subject)
//        jsonObject.put("numclass", num_class)
//        jsonObject.put("nametest", name_test)
//        jsonObject.put("numquestions", num_questions)
//
//        val jsonObjectString = jsonObject.toString()
//        return jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        job?.cancel()
//    }
}