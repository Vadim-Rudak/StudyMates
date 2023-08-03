package com.vr.app.sh.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vr.app.sh.data.model.Book
import com.vr.app.sh.data.model.User

@Dao
interface DAOBook {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(listBook: List<Book>)

    @Query("select * from Books WHERE numclass = :num_class")
    fun getBookInOneClass(num_class:Int): LiveData<List<Book>>
}