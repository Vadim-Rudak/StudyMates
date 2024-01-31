package com.vr.app.sh.data.storage.model.chat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Chat")
data class ChatEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "name") val name:String?=null,
    @ColumnInfo(name = "user_id") val userCreate:Int?=null
){

}