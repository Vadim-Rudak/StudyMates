package com.vr.app.sh.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vr.app.sh.data.storage.model.LessonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOLessons {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: LessonEntity)

    @Query("select * from Lessons WHERE numday = :numDay")
    fun getLessonsInDay(numDay:Int): Flow<List<LessonEntity>>
}