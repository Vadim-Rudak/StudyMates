package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.domain.model.response.ListResponse
import com.vr.app.sh.domain.repository.internet.QuestionsInternetRepo

class GetListQuestions(private val questionsInternetRepo: QuestionsInternetRepo) {
    suspend fun execute(idTest:Int):ListResponse<Question>{
        return questionsInternetRepo.getTestQuestions(idTest)
    }
}