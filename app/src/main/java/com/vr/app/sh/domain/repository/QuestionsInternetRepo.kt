package com.vr.app.sh.domain.repository

import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.domain.model.Test
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

interface QuestionsInternetRepo {
    suspend fun getTestQuestions(num_class:Int): Response<List<Question>>
    suspend fun sendQuestions(requestBody: RequestBody): Response<ResponseBody>
}