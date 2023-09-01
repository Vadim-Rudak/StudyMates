package com.vr.app.sh.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


class User(){
    var id: Int?=0
    var name:String?=null
    var lastName:String?=null
    var gender:String = "man"
    var dateBirthday:String?=null
    var cityLive:String?=null
    val auth = Auth()
    val school = School()
    val photo = Photo()
}


