package com.vr.app.sh.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vr.app.sh.data.model.Question
import com.vr.app.sh.data.model.User

@Dao
interface DAOQuestions {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(listQuestions: List<Question>)

    @Query("select * from Questions")
    fun getQuestions(): LiveData<List<Question>>

    @Query("DELETE FROM Questions")
    fun deleteAllRow()

    @Transaction
    suspend fun saveNewQuestions(listQuestions: List<Question>){
        deleteAllRow()
        insertQuestions(listQuestions)
    }

}