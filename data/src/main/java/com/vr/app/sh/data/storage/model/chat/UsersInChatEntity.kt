package com.vr.app.sh.data.storage.model.chat

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vr.app.sh.data.storage.model.users.UserEntity
import com.vr.app.sh.domain.model.messages.UserInChat

@Entity(tableName = "UsersInChat")
class UsersInChatEntity(
    @PrimaryKey(autoGenerate = false) val id:Int? = null,
    @ColumnInfo(name = "chat_id") val chatId:Int? = null,
    @ColumnInfo(name = "user_id") val userId:Int? = null,
    @ColumnInfo(name = "group") val group:Int? = 0,
    @Embedded (prefix = "_user") var userEntity: UserEntity? = null
){

    fun toUserInChat():UserInChat{
        val user = UserInChat(id, chatId, userId, group)
        if (userEntity!=null){
            user.user = userEntity!!.toUser()
        }
        Log.d("FFF","${userEntity.toString()}")
        return user
    }
}