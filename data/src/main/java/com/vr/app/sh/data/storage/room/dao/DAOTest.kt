package com.vr.app.sh.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vr.app.sh.data.storage.model.QuestionEntity
import com.vr.app.sh.data.storage.model.TestEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOTest {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTests(listTests: List<TestEntity>)

    @Query("select * from Tests WHERE numclass = :num_class")
    fun getTestsInOneClass(num_class:Int): Flow<List<TestEntity>>

    @Query("DELETE FROM Tests")
    fun deleteAllTests()

    @Transaction
    suspend fun saveNewTests(listTests: List<TestEntity>){
        deleteAllTests()
        insertTests(listTests)
    }
}