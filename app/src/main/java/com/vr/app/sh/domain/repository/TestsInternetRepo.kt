package com.vr.app.sh.domain.repository

import com.vr.app.sh.data.model.Test
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

interface TestsInternetRepo {
    suspend fun getListTestsInSub(name_subject:String): Response<List<Test>>
    suspend fun sendTest(requestBody: RequestBody): Response<ResponseBody>
}