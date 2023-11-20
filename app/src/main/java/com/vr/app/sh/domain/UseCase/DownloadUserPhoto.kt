package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.PhotoInternetRepo
import okhttp3.ResponseBody
import retrofit2.Call

class DownloadUserPhoto(private val photoInternetRepo: PhotoInternetRepo) {
    fun execute(userId:Int): Call<ResponseBody> {
        return photoInternetRepo.downloadPhoto(userId)
    }
}