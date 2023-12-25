package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Lesson
import com.vr.app.sh.domain.repository.local.LessonsRepo
import kotlinx.coroutines.flow.Flow

class GetLessonsInDay(private val lessonsRepo: LessonsRepo) {

    fun execute(numDay:Int): Flow<List<Lesson>> {
        return lessonsRepo.getLessonsInDay(numDay)
    }
}