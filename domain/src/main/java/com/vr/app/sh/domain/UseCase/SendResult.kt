package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.internet.ResultInternetRepo
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

class SendResult(private val resultInternetRepo: ResultInternetRepo) {
    suspend fun execute(requestBody: RequestBody): Response<ResponseBody> {
        return resultInternetRepo.sendResult(requestBody)
    }
}