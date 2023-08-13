package com.vr.app.sh.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vr.app.sh.data.model.Lesson

@Dao
interface DAOLessons {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: Lesson)

    @Query("select * from Lessons WHERE numday = :num_day")
    fun getLessonsInDay(num_day:Int): LiveData<List<Lesson>>
}