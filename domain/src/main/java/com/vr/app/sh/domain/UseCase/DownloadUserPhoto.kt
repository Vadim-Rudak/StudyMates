package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.response.SendFile
import com.vr.app.sh.domain.repository.internet.PhotoInternetRepo

class DownloadUserPhoto(private val photoInternetRepo: PhotoInternetRepo) {
    suspend fun execute(userId:Int,pathToSave:String): SendFile {
        return photoInternetRepo.downloadPhoto(userId,pathToSave)
    }
}