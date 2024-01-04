package com.vr.app.sh.data.api

import android.content.Context
import android.util.Log
import com.rv.data.R
import com.vr.app.sh.domain.model.response.SendFile
import com.vr.app.sh.domain.repository.internet.PhotoInternetRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class PhotoInternetRepoImpl(val context: Context, private val networkService: NetworkService): PhotoInternetRepo {

    override suspend fun downloadPhoto(userId: Int, pathToSave:String): SendFile {
        var sendFile = SendFile()

        val job = CoroutineScope(Dispatchers.IO).launch {
            val response = networkService.downloadUserPhoto(userId)
            if(response.isSuccessful){
                val writtenToDisk = writeResponseBodyToDisk(response.body(), pathToSave)
                Log.d("download", "file download was a success? $writtenToDisk")
                Log.d("downloadFile", "sucess")

                sendFile = if (writtenToDisk){
                    SendFile(true)
                }else{
                    val file = File(pathToSave)
                    if (file.exists()){
                        file.delete()
                    }
                    SendFile(false,context.resources.getString(R.string.alrErrorDownloadFile))
                }
            }else{
                sendFile = SendFile(false,response.message())
            }
        }
        job.join()
        job.cancel()
        return sendFile
    }

    private fun writeResponseBodyToDisk(body: ResponseBody?, filePath: String): Boolean {
        try {
            val futureStudioIconFile = File(filePath)
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
                    outputStream.write(fileReader, 0, read)
                }
                outputStream.flush()
                return true
            } catch (e: IOException) {
                return false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            return false
        }
    }

    override suspend fun verificationUser(userId: Int) {
        networkService.verificationUserInServer(userId)
    }
}