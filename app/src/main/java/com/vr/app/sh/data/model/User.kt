package com.vr.app.sh.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


class User(){
    var id:Int=0
    var name:String?=null
    var lastName:String?=null
    var gender:String? = "man"
    var dateBirthday:String?=null
    var cityLive:String?=null
    var auth = Auth()
    var school = School()
    var photo = Photo()
    override fun toString(): String {
        return "User(id=$id, name=$name, lastName=$lastName, gender=$gender, dateBirthday=$dateBirthday, cityLive=$cityLive, auth=$auth, school=$school, photo=$photo)"
    }


}


