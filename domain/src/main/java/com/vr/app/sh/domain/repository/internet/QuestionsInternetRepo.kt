package com.vr.app.sh.domain.repository.internet

import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.domain.model.response.ListResponse
import com.vr.app.sh.domain.model.response.SendResponse

interface QuestionsInternetRepo {
    suspend fun getTestQuestions(numClass:Int): ListResponse<Question>
    suspend fun sendQuestions(jsonQuestions: String): SendResponse
}