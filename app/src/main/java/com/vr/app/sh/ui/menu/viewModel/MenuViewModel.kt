package com.vr.app.sh.ui.menu.viewModel

import android.content.Context
import android.content.res.Resources
import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.CleanCookie
import com.vr.app.sh.domain.UseCase.CleanUser
import com.vr.app.sh.domain.UseCase.DownloadUserPhoto
import com.vr.app.sh.domain.UseCase.GetAllBookListInternet
import com.vr.app.sh.domain.UseCase.SaveBookListInBD
import com.vr.app.sh.ui.menu.adapter.TopMenuAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class MenuViewModel(
    context: Context,
    val getAllBookListInternet: GetAllBookListInternet,
    val saveBookListInBD: SaveBookListInBD,
    val downloadUserPhoto: DownloadUserPhoto,
    private val cleanUser: CleanUser,
    private val cleanCookie: CleanCookie,
    private val internetConnect:Boolean
): ViewModel() {

    val res: Resources = context.resources
    val adapter = TopMenuAdapter(res)
    val errorMessage = MutableLiveData<String>()
    val statusListBook = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    var job: Job? = null

    init {
        createDirs()
    }

    fun logout(){
        cleanCookie.execute()
        cleanUser.execute()
    }

    fun createDirs(){
        val path = "${Environment.getExternalStorageDirectory().path}/SchoolProg/Books/Class_"
        var fileDir:File
        for (i in 1..11){
            fileDir = File(path+i)
            if (!fileDir.exists()){
                fileDir.mkdirs()
            }
        }
    }

    fun downloadUserPhoto(userId:Int, pathPhoto:String){
        if(internetConnect){
            Log.d("download", "download user photo start")
            job = CoroutineScope(Dispatchers.IO).launch {
                downloadUserPhoto.execute(userId,pathPhoto).collectIndexed { _, value ->
                    withContext(Dispatchers.Main){
                        if (value.success == true){
                            errorMessage.value = value.infoToSend
                        }
                    }
                }
            }
        }else{
            errorMessage.value = res.getString(R.string.alrNotInternetConnection)
        }
    }

    fun getAllBooks(){
        if (internetConnect){
            job = CoroutineScope(Dispatchers.IO).launch {
                loading.postValue(true)
                statusListBook.postValue(false)
                getAllBookListInternet.execute().also {
                    if (it.success){
                        statusListBook.postValue(true)
                        withContext(Dispatchers.Main){
                            loading.postValue(false)
                        }
                        it.list?.let { it1 -> saveBookListInBD.execute(it1) }
                    }else{
                        errorMessage.value = res.getString(R.string.alrErrorGetData)
                    }
                }
            }
        }else{
            errorMessage.value = res.getString(R.string.alrNotInternetConnection)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}