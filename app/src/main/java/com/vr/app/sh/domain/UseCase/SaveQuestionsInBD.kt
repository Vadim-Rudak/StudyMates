package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.data.model.Question
import com.vr.app.sh.data.repository.DAOQuestions

class SaveQuestionsInBD(private val questionsDAO:DAOQuestions) {
    suspend fun execute(ListQuestions:List<Question>){
        return questionsDAO.saveNewQuestions(ListQuestions)
    }
}