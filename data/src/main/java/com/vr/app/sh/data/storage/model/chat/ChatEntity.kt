package com.vr.app.sh.data.storage.model.chat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vr.app.sh.domain.model.messages.Chat

@Entity(tableName = "Chat")
data class ChatEntity(
    @PrimaryKey(autoGenerate = false) val id:Int = 0,
    @ColumnInfo(name = "name") val name:String?=null,
    @ColumnInfo(name = "user_id") val userCreate:Int?=null
){
    fun toChat() = Chat(id, name, userCreate)
}