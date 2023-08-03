package com.vr.app.sh.ui.tests.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    fun errorMessage(textMessage:String,context: Context){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Ошибка")
        alertDialog.setMessage(textMessage)
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        alertDialog.show()
    }

    fun sendResult(test_id:Int,test_result:Int,num_correct_otv: Int,num_error_otv: Int,num_no_otv: Int){
        if (internetConnection.UseInternet()){
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = sendResult.execute(JSONResult(test_id, getUserBD.execute().user_name, test_result,
                    num_correct_otv, num_error_otv, num_no_otv))

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

    fun JSONResult(id_test:Int,username:String,score:Int,num_correct_otv:Int,num_error_otv:Int,num_no_otv:Int): RequestBody {
        val jsonObject = JSONObject()
        jsonObject.put("idtest", id_test)
        jsonObject.put("username", username)
        jsonObject.put("score", score)
        jsonObject.put("num_correct_otv", num_correct_otv)
        jsonObject.put("num_error_otv", num_error_otv)
        jsonObject.put("num_no_otv", num_no_otv)

        val jsonObjectString = jsonObject.toString()
        return jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}