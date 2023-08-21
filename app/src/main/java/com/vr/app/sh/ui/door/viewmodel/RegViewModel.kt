package com.vr.app.sh.ui.door.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.Registration
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class RegViewModel(private val resources: Resources, val registration: Registration, val internetConnection: InternetConnection): ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val statusRegistration = MutableLiveData<Boolean>()
    val numFragment = MutableLiveData<Int>()
    var job: Job? = null

    init {
        numFragment.postValue(0)
    }

    fun registration(
        login:String,
        password1:String,
        password2:String,
        name:String,
        last_name:String,
        patronymic:String,
        date_birthday:String,
        num_class: Int
    ){
        if (internetConnection.UseInternet()){
            if (!TextUtils.isEmpty(login.trim())&&!TextUtils.isEmpty(password1.trim())
                &&!TextUtils.isEmpty(password2.trim())){
                if (password1.equals(password2)){
                    job = CoroutineScope(Dispatchers.IO).launch {

                        val reg = registration.execute(JSONObjectUser(login,password1,name,last_name,patronymic,
                        date_birthday,num_class))

                        withContext(Dispatchers.Main) {
                            if (reg.status_reg == true) {
                                statusRegistration.value = true
                            } else {
                                errorMessage.value = reg.message
                            }
                        }
                    }
                }else{
                    errorMessage.value = resources.getString(R.string.alrPasswordsNotEquals)
                }
            }else{
                errorMessage.value = resources.getString(R.string.alrNotTextInInput)
            }
        }else{
            errorMessage.value = resources.getString(R.string.alrNotInternetConnection)
        }
    }

    fun JSONObjectUser(login:String,password:String,name:String,last_name:String,patronymic:String,date_birthday:String,num_class:Int): RequestBody {
        val jsonObject = JSONObject()
        jsonObject.put("login", login)
        jsonObject.put("password", password)
        jsonObject.put("name", name)
        jsonObject.put("last_name", last_name)
        jsonObject.put("patronymic", patronymic)
        jsonObject.put("date_birthday", date_birthday)
        jsonObject.put("num_class", num_class)

        val jsonObjectString = jsonObject.toString()
        return jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}