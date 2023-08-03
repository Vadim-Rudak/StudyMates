package com.vr.app.sh.domain.UseCase

import androidx.lifecycle.LiveData
import com.vr.app.sh.data.model.Question
import com.vr.app.sh.data.repository.DAOQuestions

class GetListQuestionsBD(private val questionsRepo: DAOQuestions) {
    fun execute(): LiveData<List<Question>> {
        return questionsRepo.getQuestions()
    }
}