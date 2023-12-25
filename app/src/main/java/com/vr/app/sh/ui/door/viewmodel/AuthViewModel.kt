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
import com.vr.app.sh.domain.UseCase.DownloadUserPhoto
import com.vr.app.sh.domain.UseCase.SaveUser
import com.vr.app.sh.ui.other.UseAlert
import kotlinx.coroutines.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class AuthViewModel(private val resources: Resources, private val cleanUser: CleanUser, private val downloadUserPhoto: DownloadUserPhoto, val authorization: Authorization, private val saveUser: SaveUser,private val internetConnect:Boolean): ViewModel() {

    val loadingAlert = UseAlert.loading(
        resources.getString(R.string.alrLoadingAuthTitel),
        resources.getString(R.string.alrLoadingAuthText1)
    )
    var saveFileInMemory:Boolean = false
    val errorMessage = MutableLiveData<String>()
    val statusAuth = MutableLiveData<Boolean>()
    var job: Job? = null

    fun authorization(context: Context,fragmentManager: FragmentManager,login:String,password:String){
        cleanUser.execute()
        if (internetConnect){
            if (!TextUtils.isEmpty(login.trim())&&!TextUtils.isEmpty(password.trim())){
                loadingAlert.show(fragmentManager,"AlertLoading")
                job = CoroutineScope(Dispatchers.IO).launch {
                    val auth = authorization.execute(authUserInfo(login,password))

                    withContext(Dispatchers.Main){
                        if (auth.status == true){
                            saveUser.execute(auth.user!!)
                            downloadUserPhoto(context, auth.user!!.id)
                        }else{
                            errorMessage.value = auth.message
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

    private fun authUserInfo(userName: String, password: String): RequestBody {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("username", userName)
            .addFormDataPart("password", password)
            .build()
    }

    private suspend fun downloadUserPhoto(context: Context,userId:Int){
        Log.d("downloadFile", "start")
        val sharedPrefs = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val pathSave = "${Environment.getExternalStorageDirectory().path}/SchoolProg/MyProfile/${sharedPrefs.getString("photo_name",null)}"
        val call = downloadUserPhoto.execute(userId)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                try {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (response!!.isSuccessful){
                            val writtenToDisk = writeResponseBodyToDisk(response.body(), pathSave)
                            Log.d("download", "file download was a success? $writtenToDisk")
                            Log.d("downloadFile", "sucess")
                            saveFileInMemory = writtenToDisk
                            if (!writtenToDisk){
                                val file = File(pathSave)
                                if (file.exists()){
                                    file.delete()
                                }
                                withContext(Dispatchers.Main){
                                    errorMessage.value = resources.getString(R.string.alrErrorDownloadBook)
                                }
                            }
                            withContext(Dispatchers.Main){
                                loadingAlert.isDone()
                                statusAuth.value = true
                            }
                        }else{
                            errorMessage.value = response.message()
                        }
                    }
                    Log.d("onResponse", "Response came from server")
                } catch (e: IOException) {
                    Log.d("onResponse", "There is an error")
                    e.printStackTrace()
                }
            }
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.d("onFailure", t.toString())
            }
        })
    }

    private fun writeResponseBodyToDisk(body: ResponseBody?, path_book: String): Boolean {
        try {

            var futureStudioIconFile = File(path_book)
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null

            try {
                val fileReader = ByteArray(4096)

                inputStream = body?.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)

                while (true) {
                    val read = inputStream!!.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream!!.write(fileReader, 0, read)
                }
                outputStream!!.flush()
                return true
            } catch (e: IOException) {
                return false
            } finally {
                if (inputStream != null) {
                    inputStream!!.close()
                }
                if (outputStream != null) {
                    outputStream!!.close()
                }
            }
        } catch (e: IOException) {
            return false
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}