package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.internet.QuestionsInternetRepo
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

class SendQuestions(private val questionsInternetRepo: QuestionsInternetRepo) {
    suspend fun execute(requestBody: RequestBody): Response<ResponseBody> {
        return questionsInternetRepo.sendQuestions(requestBody)
    }
}