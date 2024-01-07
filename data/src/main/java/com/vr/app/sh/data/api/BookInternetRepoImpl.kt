package com.vr.app.sh.data.api

import android.content.Context
import android.util.Log
import com.rv.data.R
import com.vr.app.sh.data.storage.WriteResponseToStorage
import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.model.response.DownloadFile
import com.vr.app.sh.domain.model.response.ListResponse
import com.vr.app.sh.domain.repository.internet.BookInternetRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.net.URLConnection

class BookInternetRepoImpl(val context: Context, private val networkService: NetworkService): BookInternetRepo {
    override suspend fun getAllBookList(): ListResponse<Book> {
        val listBooks = networkService.getAllBooks()
        return if (listBooks.isSuccessful){
            ListResponse(true,listBooks.body())
        }else{
            ListResponse()
        }
    }

    override suspend fun getBookFile(bookId: Int, pathToSave:String): Flow<DownloadFile> = flow{
        val downloadFile = DownloadFile()

        val response = networkService.downloadBook(bookId)
        if(response.isSuccessful){
            val writtenToDisk = WriteResponseToStorage().write(response.body(),pathToSave)
            Log.d("downloadFile", "sucess")

            writtenToDisk.collect{
                downloadFile.progress = it.progress
                emit(downloadFile)
                if (it.status !=null){
                    if (it.status!!){
                        downloadFile.success = true
                        emit(DownloadFile(true,100))
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

    override suspend fun addBook(numClass: Int, nameBook: String, bookFile: File): Boolean {
        val fileBook: MultipartBody.Part? = prepareFilePart("fileBook",bookFile)
        return networkService.addBook(numClass, nameBook, fileBook).isSuccessful
    }

    private fun prepareFilePart(partName: String, file: File): MultipartBody.Part? {
        if (!file.exists()){
            val requestFile: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "")
            // MultipartBody.Part is used to send also the actual file name
            return MultipartBody.Part.createFormData(partName, file.name, requestFile)
        }else{
            val mimeType2: String = URLConnection.guessContentTypeFromName(file.name)
            return if (mimeType2 != null) {
                val requestFile: RequestBody = RequestBody.create(mimeType2.toMediaTypeOrNull(), file)
                // MultipartBody.Part is used to send also the actual file name
                MultipartBody.Part.createFormData(partName, file.name, requestFile)
            } else {
                null
            }
        }
    }
}