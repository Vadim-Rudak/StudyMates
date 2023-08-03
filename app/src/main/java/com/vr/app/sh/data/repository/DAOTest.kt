package com.vr.app.sh.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vr.app.sh.data.model.Test

@Dao
interface DAOTest {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTests(listTests: List<Test>)

    @Query("select * from Tests WHERE numclass = :num_class")
    fun getTestsInOneClass(num_class:Int): LiveData<List<Test>>

}