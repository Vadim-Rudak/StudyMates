package com.vr.app.sh.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vr.app.sh.data.storage.model.chat.UsersInChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOUsersInChat {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInChat(userInChat: UsersInChatEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersInChat(usersInChat: List<UsersInChatEntity>)

    @Query("select * from UsersInChat")
    fun getAllChats(): Flow<List<UsersInChatEntity>>

    @Query("select u.id,u.chat_id,u.user_id," +
            "User.id as _userid,User.name as _username, User.lastname as _userlastname," +
            "User.gender as _usergender,User.datebirthday as _userdatebirthday, User.citylive as _usercitylive " +
            "from UsersInChat u inner join User on u.user_id = User.id;")
    fun getAllChatsWithUsers(): Flow<List<UsersInChatEntity>>

    @Query("select u.chat_id from UsersInChat u where u.user_id = :userId;")
    fun getChatId(userId:Int):Int?=null
}