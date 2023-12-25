package com.vr.app.sh.domain.repository.internet

import com.vr.app.sh.domain.model.Test
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

interface TestsInternetRepo {
    suspend fun getListTestsInSub(nameSubject:String): Response<List<Test>>
    suspend fun sendTest(requestBody: RequestBody): Response<ResponseBody>
}