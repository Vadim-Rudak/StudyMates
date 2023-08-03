package com.vr.app.sh.ui.tests.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.vr.app.sh.data.model.Question
import com.vr.app.sh.domain.UseCase.*
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class AddTestViewModel(val getListTestsInternet: GetListTestsInternet,val saveTestsInBD: SaveTestsInBD,
                       val sendTestInfo: SendTestInfo, val sendQuestions: SendQuestions,
                       val internetConnection: InternetConnection): ViewModel() {

    var listQuestions:ArrayList<Question> = ArrayList()
    val errorMessage = MutableLiveData<String>()
    val vizibleProgressBar = MutableLiveData<Boolean>()
    var job: Job? = null

    fun errorMessage(textMessage:String,context: Context){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Ошибка")
        alertDialog.setMessage(textMessage)
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        alertDialog.show()
    }

    fun sendTestWithQuestions(name_subject:String,num_class:Int,name_test: String){
        if (internetConnection.UseInternet()){
            vizibleProgressBar.value = true
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = sendTestInfo.execute(getJSONTest(name_subject,num_class, name_test,listQuestions.size))
                val gs = Gson()
                val jsonQuestions = gs.toJson(listQuestions)
                val resp = sendQuestions.execute(jsonQuestions.toRequestBody("application/json".toMediaTypeOrNull()))
                val resp2 = getListTestsInternet.execute(name_subject)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful&&resp.isSuccessful) {
                        if (resp2.isSuccessful){
                            saveTestsInBD.execute(resp2.body()!!)
                            vizibleProgressBar.value = false
                        }
                    } else {
                        vizibleProgressBar.value = false
                        errorMessage.value = "Ошибка отправки теста"
                    }
                }
            }
        }else{
            errorMessage.value = "Нет подключения к интернету"
        }
    }

    fun getJSONTest(subject:String, num_class: Int?, name_test:String, num_questions:Int): RequestBody {
        val jsonObject = JSONObject()
        jsonObject.put("subject", subject)
        jsonObject.put("numclass", num_class)
        jsonObject.put("nametest", name_test)
        jsonObject.put("numquestions", num_questions)

        val jsonObjectString = jsonObject.toString()
        return jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}