package com.vr.app.sh.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.vr.app.sh.data.storage.model.chat.UsersInChatEntity

@Dao
interface DAOUsersInChat {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInChat(userInChat: UsersInChatEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersInChat(usersInChat: List<UsersInChatEntity>)



}