package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.response.DownloadFile
import com.vr.app.sh.domain.repository.internet.PhotoInternetRepo
import kotlinx.coroutines.flow.Flow

class DownloadUserPhoto(private val photoInternetRepo: PhotoInternetRepo) {
    suspend fun execute(userId:Int,pathToSave:String): Flow<DownloadFile> {
        return photoInternetRepo.downloadPhoto(userId,pathToSave)
    }
}