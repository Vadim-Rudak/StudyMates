package com.vr.app.sh.data.storage.model.chat

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vr.app.sh.data.storage.model.users.UserEntity

@Entity(tableName = "FavoriteUser")
data class FavoriteUserEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "user_id") val userId:Int = 0,
    @Embedded(prefix = "_user") var userEntity: UserEntity? = null
)