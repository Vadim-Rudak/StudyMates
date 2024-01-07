package com.vr.app.sh.data.storage.room.repo

import com.vr.app.sh.data.storage.model.QuestionEntity
import com.vr.app.sh.data.storage.room.dao.DAOQuestions
import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.domain.repository.local.QuestionsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuestionsRepoImpl(private val questionsDAO:DAOQuestions): QuestionsRepo {

    override fun getAllQuestions(): Flow<List<Question>> {
        return questionsDAO.getQuestions().map{
            it.map{questionEntity ->
                questionEntity.toQuestion()
            }
        }
    }

    override suspend fun saveNewQuestions(listQuestions: List<Question>) {
        questionsDAO.saveNewQuestions(listQuestions.map { QuestionEntity(
            id = it.id,
            question = it.question,
            otv1 = it.answer1,
            otv2 = it.answer2,
            otv3 = it.answer3,
            otv4 = it.answer4,
            correct_otv = it.correctAnswer,
            test_id = it.test_id
        ) })
    }
}