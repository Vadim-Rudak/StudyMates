package com.vr.app.sh.domain.repository.local

import com.vr.app.sh.domain.model.Lesson
import kotlinx.coroutines.flow.Flow

interface LessonsRepo {
    suspend fun insertLesson(lesson: Lesson)
    fun getLessonsInDay(numDay:Int): Flow<List<Lesson>>
}