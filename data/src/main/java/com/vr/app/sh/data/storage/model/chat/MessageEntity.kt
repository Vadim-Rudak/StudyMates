package com.vr.app.sh.data.storage.model.chat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Message")
class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "chat_id") val chatId:Int = 0,
    @ColumnInfo(name = "user_to_send_id") val userToSendId:Int = 0,
    @ColumnInfo(name = "type") val type:Int = 0,
    @ColumnInfo(name = "time_send") val timeSend:Int = 0,
    @ColumnInfo(name = "res") val res:String = ""
)