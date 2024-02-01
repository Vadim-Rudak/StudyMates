package com.vr.app.sh.data.storage.room.dao.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vr.app.sh.data.storage.model.QuestionEntity
import com.vr.app.sh.data.storage.model.users.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOUser {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(listUsers: List<UserEntity>)

    @Query("select * from User")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("DELETE FROM User")
    fun deleteAllRow()

    @Transaction
    suspend fun saveNewUsers(listUsers: List<UserEntity>){
        deleteAllRow()
        insertUsers(listUsers)
    }

}