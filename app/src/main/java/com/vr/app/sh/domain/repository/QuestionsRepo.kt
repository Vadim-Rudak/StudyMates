package com.vr.app.sh.domain.repository

import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.model.Question

interface QuestionsRepo {
    fun getQuestions(): ArrayList<Question>
    fun saveQuestionsInDB(listQuestions: List<Question>)
}