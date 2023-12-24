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
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveBookListInBD
import com.vr.app.sh.ui.menu.adapter.TopMenuAdapter
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class MenuViewModel(
    context: Context,
    val getAllBookListInternet: GetAllBookListInternet,
    val saveBookListInBD: SaveBookListInBD,
    val internetConnection: InternetConnection,
    val downloadUserPhoto: DownloadUserPhoto,
    private val cleanUser: CleanUser,
    private val cleanCookie: CleanCookie
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

    fun downloadUserPhoto(userId:Int,pathPhoto:String){
        Log.d("FFF","$userId  fdsf=> $pathPhoto")
        if(internetConnection.UseInternet()){
            Log.d("download", "download user photo start")
            val call = downloadUserPhoto.execute(userId)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    try {
                        CoroutineScope(Dispatchers.IO).launch {
                            if (response!!.isSuccessful){
                                val writtenToDisk = writeResponseBodyToDisk(response.body(), pathPhoto)

                                Log.d("download", "download sucess")

                                if (!writtenToDisk){
//                                    val file = File(pathPhoto)
//                                    if (file.exists()){
//                                        file.delete()
//                                    }
                                    withContext(Dispatchers.Main){
                                        errorMessage.value = res.getString(R.string.alrErrorDownloadBook)
                                    }
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
        }else{
            errorMessage.value = res.getString(R.string.alrNotInternetConnection)
        }
    }

    private suspend fun writeResponseBodyToDisk(body: ResponseBody?, path_book: String): Boolean {
        try {

            var futureStudioIconFile = File(path_book)
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null

            try {
                val fileReader = ByteArray(4096)

                val fileSize = body?.contentLength()
                var fileSizeDownloaded: Long = 0

                inputStream = body?.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)

                while (true) {
                    val read = inputStream!!.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream!!.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
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


    fun getAllBooks(){
        if (internetConnection.UseInternet()){
            job = CoroutineScope(Dispatchers.IO).launch {
                loading.postValue(true)
                statusListBook.postValue(false)
                withContext(Dispatchers.Main){
                    val ListBooks = getAllBookListInternet.execute()
                    if (ListBooks.isSuccessful){
                        loading.postValue(false)
                        statusListBook.postValue(true)
                        saveBookListInBD.execute(ListBooks.body()!!)
                    }else{
                        loading.postValue(false)
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