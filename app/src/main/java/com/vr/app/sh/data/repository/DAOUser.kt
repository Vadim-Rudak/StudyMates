package com.vr.app.sh.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vr.app.sh.data.model.User

@Dao
interface DAOUser {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("DELETE FROM User")
    fun deleteAllRow()

    @Query("SELECT * from User")
    fun getUser():List<User>

    @Transaction
    suspend fun saveUserNow(user: User){
        deleteAllRow()
        insertUser(user = user)
    }

}