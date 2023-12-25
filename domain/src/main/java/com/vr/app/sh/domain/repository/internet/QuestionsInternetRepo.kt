package com.vr.app.sh.domain.repository.internet

import com.vr.app.sh.domain.model.Question
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

interface QuestionsInternetRepo {
    suspend fun getTestQuestions(num_class:Int): Response<List<Question>>
    suspend fun sendQuestions(requestBody: RequestBody): Response<ResponseBody>
}