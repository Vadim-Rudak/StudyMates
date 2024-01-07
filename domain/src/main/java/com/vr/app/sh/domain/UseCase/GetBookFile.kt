package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.response.DownloadFile
import com.vr.app.sh.domain.repository.internet.BookInternetRepo
import kotlinx.coroutines.flow.Flow

class GetBookFile(private val bookInternetRepo: BookInternetRepo) {
    suspend fun execute(idBook:Int, pathToSave:String): Flow<DownloadFile> {
        return bookInternetRepo.getBookFile(idBook,pathToSave)
    }
}