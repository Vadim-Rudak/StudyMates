package com.vr.app.sh.domain.repository.internet

import okhttp3.ResponseBody
import retrofit2.Call

interface PhotoInternetRepo {
    fun downloadPhoto(userId: Int): Call<ResponseBody>
    suspend fun verificationUser(userId: Int)
}