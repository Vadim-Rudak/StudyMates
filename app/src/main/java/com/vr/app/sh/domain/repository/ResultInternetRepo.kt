package com.vr.app.sh.domain.repository

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

interface ResultInternetRepo {
    suspend fun sendResult(requestBody: RequestBody): Response<ResponseBody>
}