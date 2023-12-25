package com.vr.app.sh.domain.repository.local

import com.vr.app.sh.domain.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionsRepo {
    fun getAllQuestions():Flow<List<Question>>
    suspend fun saveNewQuestions(listQuestions: List<Question>)
}