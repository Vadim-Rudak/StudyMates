package com.vr.app.sh.data.api

import android.content.Context
import android.util.Log
import com.rv.data.R
import com.vr.app.sh.data.storage.WriteResponseToStorage
import com.vr.app.sh.domain.model.response.DownloadFile
import com.vr.app.sh.domain.repository.internet.PhotoInternetRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class PhotoInternetRepoImpl(val context: Context, private val networkService: NetworkService): PhotoInternetRepo {

    override suspend fun downloadPhoto(userId: Int, pathToSave:String): Flow<DownloadFile> = flow{
        val response = networkService.downloadUserPhoto(userId)
        if(response.isSuccessful){
            val writtenToDisk = WriteResponseToStorage().write(response.body(), pathToSave)
            Log.d("downloadFile", "sucess")

            writtenToDisk.collect{
                if (it.status !=null){
                    if (it.status == true){
                        emit(DownloadFile(true))
                    }else{
                        val file = File(pathToSave)
                        if (file.exists()){
                            file.delete()
                        }
                        emit(DownloadFile(false,context.resources.getString(R.string.alrErrorDownloadFile)))
                    }
                }
            }
        }else{
            emit(DownloadFile(false,response.message()))
        }
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