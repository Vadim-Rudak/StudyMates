package com.vr.app.sh.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vr.app.sh.data.storage.model.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOBook {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(listBook: List<BookEntity>)

    @Query("select * from Books WHERE numclass = :num_class")
    fun getBookInOneClass(num_class:Int): Flow<List<BookEntity>>
}