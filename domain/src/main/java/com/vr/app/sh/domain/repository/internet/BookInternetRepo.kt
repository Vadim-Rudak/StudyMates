package com.vr.app.sh.domain.repository.internet

import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.model.response.DownloadFile
import com.vr.app.sh.domain.model.response.ListResponse
import kotlinx.coroutines.flow.Flow
import java.io.File

interface BookInternetRepo {
    suspend fun getAllBookList(): ListResponse<Book>
    suspend fun getBookFile(idBook:Int,pathToSave:String): Flow<DownloadFile>
    suspend fun addBook(numClass:Int,nameBook:String,bookFile: File):Boolean
}