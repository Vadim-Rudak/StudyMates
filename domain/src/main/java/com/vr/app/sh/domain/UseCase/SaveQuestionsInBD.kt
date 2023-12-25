package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.domain.repository.local.QuestionsRepo

class SaveQuestionsInBD(private val questionsRepo: QuestionsRepo) {
    suspend fun execute(listQuestions:List<Question>){
        return questionsRepo.saveNewQuestions(listQuestions)
    }
}