package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.domain.repository.QuestionsRepo

class SaveQuestionsInBD(private val questionsRepo: QuestionsRepo) {
    fun execute(ListQuestions:List<Question>){
        return questionsRepo.saveQuestionsInDB(ListQuestions)
    }
}