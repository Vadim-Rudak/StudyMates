package com.vr.app.sh.domain.UseCase

import androidx.lifecycle.LiveData
import com.vr.app.sh.data.model.Lesson
import com.vr.app.sh.data.repository.DAOLessons

class GetLessonsInDay(private val daoLessons: DAOLessons) {
    fun execute(numDay:Int):LiveData<List<Lesson>>{
        return daoLessons.getLessonsInDay(numDay)
    }
}