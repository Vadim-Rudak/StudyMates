package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.internet.TestsInternetRepo
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

class SendTestInfo(private val testsInternetRepo: TestsInternetRepo) {
    suspend fun execute(requestBody: RequestBody): Response<ResponseBody> {
        return testsInternetRepo.sendTest(requestBody)
    }
}