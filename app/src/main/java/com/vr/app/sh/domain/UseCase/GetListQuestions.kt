package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.domain.repository.QuestionsInternetRepo
import retrofit2.Response

class GetListQuestions(private val questionsInternetRepo: QuestionsInternetRepo) {
    suspend fun execute(id_test:Int):Response<List<Question>>{
        return  questionsInternetRepo.getTestQuestions(id_test)
    }
}