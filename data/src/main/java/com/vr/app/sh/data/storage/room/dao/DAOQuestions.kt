package com.vr.app.sh.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vr.app.sh.data.storage.model.QuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOQuestions {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(listQuestions: List<QuestionEntity>)

    @Query("select * from Questions")
    fun getQuestions(): Flow<List<QuestionEntity>>

    @Query("DELETE FROM Questions")
    fun deleteAllRow()

    @Transaction
    suspend fun saveNewQuestions(listQuestions: List<QuestionEntity>){
        deleteAllRow()
        insertQuestions(listQuestions)
    }

}