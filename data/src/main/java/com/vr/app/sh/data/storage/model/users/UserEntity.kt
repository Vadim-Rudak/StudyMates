package com.vr.app.sh.data.storage.model.users

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vr.app.sh.data.storage.model.chat.FavoriteUserEntity
import com.vr.app.sh.domain.model.User

@Entity(tableName = "User")
class UserEntity(
    @PrimaryKey(autoGenerate = false) var id:Int=0,
    @ColumnInfo(name = "name") var name:String?=null,
    @ColumnInfo(name = "lastname") var lastName:String?=null,
    @ColumnInfo(name = "gender") var gender:String? = "man",
    @ColumnInfo(name = "datebirthday") var dateBirthday:String?=null,
    @ColumnInfo(name = "citylive") var cityLive:String?=null,
){
    fun toUser() = User(id,name,lastName,gender,dateBirthday,cityLive)
    fun fromUser(user:User) = UserEntity(
        user.id,
        user.name,
        user.lastName,
        user.gender,
        user.dateBirthday,
        user.cityLive
    )
}