package com.vr.app.sh.domain.repository

import okhttp3.ResponseBody
import retrofit2.Call

interface PhotoInternetRepo {
    fun downloadPhoto(userId: Int): Call<ResponseBody>
}