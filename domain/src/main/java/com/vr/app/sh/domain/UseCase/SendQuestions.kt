package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.response.SendResponse
import com.vr.app.sh.domain.repository.internet.QuestionsInternetRepo

class SendQuestions(private val questionsInternetRepo: QuestionsInternetRepo) {
    suspend fun execute(jsonQuestions: String): SendResponse {
        return questionsInternetRepo.sendQuestions(jsonQuestions)
    }
}