package com.vr.app.sh.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vr.app.sh.data.storage.model.chat.FavoriteUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOFavoriteUser {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteUserEntity: FavoriteUserEntity)

    @Query("select f.id,f.user_id," +
            "User.id as _userid,User.name as _username, User.lastname as _userlastname," +
            "User.gender as _usergender,User.datebirthday as _userdatebirthday, User.citylive as _usercitylive " +
            "from FavoriteUser f inner join User on f.user_id = User.id;")
    fun getAll(): Flow<List<FavoriteUserEntity>>

    @Query("select f.id,f.user_id," +
            "User.id as _userid,User.name as _username, User.lastname as _userlastname," +
            "User.gender as _usergender,User.datebirthday as _userdatebirthday, User.citylive as _usercitylive " +
            "from User left join FavoriteUser f on f.user_id = User.id;")
    fun getToUse(): Flow<List<FavoriteUserEntity>>


    @Query("DELETE FROM FavoriteUser WHERE user_id = :userId")
    fun deleteByUserId(userId: Int)

}