package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.domain.repository.local.QuestionsRepo
import kotlinx.coroutines.flow.Flow

class GetListQuestionsBD(private val questionsRepo: QuestionsRepo) {
    fun execute(): Flow<List<Question>> {
        return questionsRepo.getAllQuestions()
    }
}