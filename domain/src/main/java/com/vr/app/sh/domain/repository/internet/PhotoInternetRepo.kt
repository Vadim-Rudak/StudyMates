package com.vr.app.sh.domain.repository.internet

import com.vr.app.sh.domain.model.response.SendFile
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Call

interface PhotoInternetRepo {
    suspend fun downloadPhoto(userId: Int,pathToSave:String): SendFile
    suspend fun verificationUser(userId: Int)
}