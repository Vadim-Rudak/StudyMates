package com.vr.app.sh.ui.door.viewmodel

import android.content.Context
import android.content.res.Resources
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.Authorization
import com.vr.app.sh.domain.UseCase.CleanUser
import com.vr.app.sh.domain.UseCase.ConnectToWebSocket
import com.vr.app.sh.domain.UseCase.DownloadUserPhoto
import com.vr.app.sh.domain.UseCase.SaveUser
import com.vr.app.sh.ui.other.UseAlert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
    private val resources: Resources,
    private val connectToWebSocket: ConnectToWebSocket,
    private val cleanUser: CleanUser,
    private val downloadUserPhoto: DownloadUserPhoto,
    val authorization: Authorization,
    private val saveUser: SaveUser,
    private val internetConnect:Boolean
): ViewModel() {

    private val loadingAlert = UseAlert.loading(
        resources.getString(R.string.alrLoadingAuthTitel),
        resources.getString(R.string.alrLoadingAuthText1)
    )
    val errorMessage = MutableLiveData<String>()
    val statusAuth = MutableLiveData<Boolean>()
    var job: Job? = null

    fun authorization(context: Context,fragmentManager: FragmentManager,login:String,password:String){
        cleanUser.execute()
        if (internetConnect){
            if (!TextUtils.isEmpty(login.trim())&&!TextUtils.isEmpty(password.trim())){
                loadingAlert.show(fragmentManager,"AlertLoading")
                job = CoroutineScope(Dispatchers.IO).launch {
                    authorization.execute(login,password).also {
                        if (it.status == true){
                            saveUser.execute(it.user!!)
                            downloadUserPhoto(context, it.user!!.id)
                        }else{
                            withContext(Dispatchers.Main){
                                errorMessage.value = it.message
                            }
                        }
                    }
                }
            }else{
                errorMessage.value = resources.getString(R.string.alrNotTextInInput)
            }
        }else{
            errorMessage.value = resources.getString(R.string.alrNotInternetConnection)
        }
    }

    private suspend fun downloadUserPhoto(context: Context,userId:Int){
        Log.d("downloadFile", "start")
        val sharedPrefs = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val pathSave = "${Environment.getExternalStorageDirectory().path}/SchoolProg/MyProfile/${sharedPrefs.getString("photo_name",null)}"

        downloadUserPhoto.execute(userId,pathSave).collectIndexed { index, value ->
            withContext(Dispatchers.Main){
                if (value.success != null){
                    if (value.success == true){
                        loadingAlert.isDone()
                        statusAuth.value = true
                    }else{
                        errorMessage.value = value.infoToSend
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}