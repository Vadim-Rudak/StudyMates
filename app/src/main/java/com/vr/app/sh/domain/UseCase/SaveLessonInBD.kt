package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.data.model.Lesson
import com.vr.app.sh.data.repository.DAOLessons

class SaveLessonInBD(private val daoLessons: DAOLessons) {
    suspend fun execute(lesson: Lesson){
        daoLessons.insertLesson(lesson)
    }
}