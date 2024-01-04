package com.vr.app.sh.ui.door.viewmodel

import android.content.res.Resources
import android.os.Environment
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.Registration
import com.vr.app.sh.domain.UseCase.SaveUser
import com.vr.app.sh.ui.other.RegistrationInfo
import com.vr.app.sh.ui.other.UseAlert.Companion.loading
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class RegViewModel(private val resources: Resources,private val saveUser: SaveUser,val registration: Registration,private val internetConnect:Boolean): ViewModel() {

    val photoPath = "${Environment.getExternalStorageDirectory().path}/SchoolProg/MyProfile"
    val loadingAlert = loading(resources.getString(R.string.alrLoadingSendTitel),resources.getString(R.string.alrLoadingSendText1))
    val errorMessage = MutableLiveData<String>()
    val statusRegistration = MutableLiveData<Boolean>()
    val numFragment = MutableLiveData<Int>()
    var job: Job? = null

    init {
        numFragment.postValue(0)
    }

    fun registration(fragmentManager: FragmentManager,){
        if (internetConnect){

            loadingAlert.show(fragmentManager,"AlertLoading")

            job = CoroutineScope(Dispatchers.IO).launch {

                val fileUserPhoto = File(RegistrationInfo.user.photo.path)

                val reg = registration.execute(RegistrationInfo.user,fileUserPhoto)

                withContext(Dispatchers.Main) {
                    if (reg.statusReg == true) {
                        regIsSuccessful(fileUserPhoto)
                        if (reg.user == null){
                            errorMessage.value = "нет данных"
                        }else{
                            Log.d("FF4","сохранение пользователя")
                            saveUser.execute(reg.user!!)
                        }

                        delay(3000)
                        loadingAlert.isDone()
                        statusRegistration.value = true

                    } else {
                        errorMessage.value = reg.message
                    }
                }
            }
        }else{
            errorMessage.value = resources.getString(R.string.alrNotInternetConnection)
        }
    }

    suspend fun regIsSuccessful(myPhoto:File){
        createDir()
        myPhoto.copyTo(File(photoPath,"myPhoto.${myPhoto.extension}"),true)
    }

    private fun createDir(){
        val dir = File(photoPath)
        if (!dir.exists()){
            dir.mkdirs()
        }
    }


    fun userInJSON(): RequestBody {
        return Gson().toJson(RegistrationInfo.user).toRequestBody("multipart/form-data".toMediaTypeOrNull())
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}