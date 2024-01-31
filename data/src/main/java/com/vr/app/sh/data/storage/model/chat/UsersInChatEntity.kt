package com.vr.app.sh.data.storage.model.chat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsersInChat")
class UsersInChatEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "chat_id") val chatId:Int = 0,
    @ColumnInfo(name = "user_id") val userId:Int = 0,
    @ColumnInfo(name = "group") val group:Boolean = false
)