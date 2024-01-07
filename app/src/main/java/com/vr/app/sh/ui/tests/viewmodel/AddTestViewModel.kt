package com.vr.app.sh.ui.tests.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.GetListTestsInternet
import com.vr.app.sh.domain.UseCase.SaveTestsInBD
import com.vr.app.sh.domain.UseCase.SendQuestions
import com.vr.app.sh.domain.UseCase.SendTestInfo
import com.vr.app.sh.domain.model.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddTestViewModel(
    private val context: Context,
    val getListTestsInternet: GetListTestsInternet,
    val saveTestsInBD: SaveTestsInBD,
    val sendTestInfo: SendTestInfo,
    val sendQuestions: SendQuestions,
    private val internetConnect:Boolean
    ): ViewModel() {

    var listQuestions:ArrayList<Question> = ArrayList()
    val errorMessage = MutableLiveData<String>()
    val vizibleProgressBar = MutableLiveData<Boolean>()
    var job: Job? = null

    fun sendTestWithQuestions(nameSubject:String, numClass:Int, nameTest: String){
        if (internetConnect){
            vizibleProgressBar.value = true
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = sendTestInfo.execute(nameSubject,numClass,nameTest,listQuestions.size)
                val jsonQuestions = Gson().toJson(listQuestions)
                val responseSendQuestions = sendQuestions.execute(jsonQuestions)
                val responseListTests = getListTestsInternet.execute(nameSubject)

                withContext(Dispatchers.Main) {
                    if (response.success&&responseSendQuestions.success) {
                        if (responseListTests.success){
                            responseListTests.list?.let { saveTestsInBD.execute(it) }
                            vizibleProgressBar.value = false
                        }
                    } else {
                        vizibleProgressBar.value = false
                        errorMessage.value = context.resources.getString(R.string.alrErrorSendTest)
                    }
                }
            }
        }else{
            errorMessage.value = context.resources.getString(R.string.alrNotInternetConnection)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}