package com.vr.app.sh.ui.door.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.data.repository.UserRepoImpl
import com.vr.app.sh.domain.UseCase.Authorization
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SetUserInBD
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.ui.menu.view.TopMenu
import kotlinx.coroutines.*
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AuthViewModel(val authorization: Authorization,val setUserInBD: SetUserInBD,val internetConnection: InternetConnection): ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val statusAuth = MutableLiveData<Boolean>()
    var job: Job? = null

    fun errorMessage(textMessage:String,context:Context){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Ошибка")
        alertDialog.setMessage(textMessage)
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        alertDialog.show()
    }

    fun authorization(login:String,password:String){
        if (internetConnection.UseInternet()){
            if (!TextUtils.isEmpty(login.trim())&&!TextUtils.isEmpty(password.trim())){
                job = CoroutineScope(Dispatchers.IO).launch {

                    val auth = authorization.execute(userInfo(login,password))
                    withContext(Dispatchers.Main){
                        if (auth.status == true){
                            setUserInBD.execute(User(login,auth.role))
                            statusAuth.value = true
                        }else{
                            errorMessage.value = auth.message
                        }
                    }
                }
            }else{
                errorMessage.value = "Заполните поля"
            }
        }else{
            errorMessage.value = "Нет подключения к интернету"
        }
    }

    fun userInfo(userName: String, password: String): RequestBody {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("username", userName)
            .addFormDataPart("password", password)
            .build()
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}