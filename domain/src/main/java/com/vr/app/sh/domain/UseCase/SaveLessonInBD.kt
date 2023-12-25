package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Lesson
import com.vr.app.sh.domain.repository.local.LessonsRepo

class SaveLessonInBD(private val lessonsRepo: LessonsRepo) {
    suspend fun execute(lesson: Lesson){
        lessonsRepo.insertLesson(lesson)
    }
}