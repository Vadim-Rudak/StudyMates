package com.vr.app.sh.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(

    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "username") var user_name:String,
    @ColumnInfo(name = "role") var role:String?=null

)


