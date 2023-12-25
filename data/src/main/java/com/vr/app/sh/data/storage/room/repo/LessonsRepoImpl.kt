package com.vr.app.sh.data.storage.room.repo

import com.vr.app.sh.data.storage.model.LessonEntity
import com.vr.app.sh.data.storage.room.dao.DAOLessons
import com.vr.app.sh.domain.model.Lesson
import com.vr.app.sh.domain.repository.local.LessonsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LessonsRepoImpl(private val lessonsDAO:DAOLessons): LessonsRepo {
    override suspend fun insertLesson(lesson: Lesson) {
        lessonsDAO.insertLesson(LessonEntity(
            id = lesson.id,
            num_day = lesson.num_day,
            name = lesson.name,
            timeStart = lesson.timeStart,
            timeEnd = lesson.timeEnd,
            num_class = lesson.num_class
        ))
    }

    override fun getLessonsInDay(numDay: Int): Flow<List<Lesson>> {
        return lessonsDAO.getLessonsInDay(numDay).map {
            it.map {lessonEntity ->
                lessonEntity.toLesson()
            }
        }
    }
}