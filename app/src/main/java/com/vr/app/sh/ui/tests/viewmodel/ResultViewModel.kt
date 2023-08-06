package com.vr.app.sh.ui.tests.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.data.model.ResultTest
import com.vr.app.sh.domain.UseCase.GetUserBD
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SendResult
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class ResultViewModel(private val internetConnection: InternetConnection,private val getUserBD: GetUserBD,private val sendResult: SendResult): ViewModel() {

    val status_send = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null

    fun sendResult(result:ResultTest){
        if (internetConnection.UseInternet()){
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = sendResult.execute(JSONResult(result))

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        status_send.value = true
                    } else {
                        errorMessage.value = "Ошибка отправки теста"
                    }
                }
            }
        }else{
            errorMessage.value = "Нет подключения к интернету"
        }
    }

    fun JSONResult(resultTest:ResultTest): RequestBody {
        val jsonObject = JSONObject()
        jsonObject.put("idtest", resultTest.test_id)
        jsonObject.put("username", getUserBD.execute().user_name)
        jsonObject.put("score", resultTest.all_result)
        jsonObject.put("num_correct_otv", resultTest.num_correct_answer)
        jsonObject.put("num_error_otv", resultTest.num_wrong_answer)
        jsonObject.put("num_no_otv", resultTest.num_not_answer)

        val jsonObjectString = jsonObject.toString()
        return jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}