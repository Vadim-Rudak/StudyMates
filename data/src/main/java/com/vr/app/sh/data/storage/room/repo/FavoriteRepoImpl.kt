package com.vr.app.sh.data.storage.room.repo

import com.vr.app.sh.data.storage.model.chat.FavoriteUserEntity
import com.vr.app.sh.data.storage.model.users.UserEntity
import com.vr.app.sh.data.storage.room.dao.DAOFavoriteUser
import com.vr.app.sh.domain.model.messages.FavoriteUser
import com.vr.app.sh.domain.repository.local.chat.FavoriteRepo
import kotlinx.coroutines.flow.*

class FavoriteRepoImpl(private var daoFavoriteUser: DAOFavoriteUser) : FavoriteRepo {
    override suspend fun insert(favoriteUser: FavoriteUser) {
        daoFavoriteUser.insert(FavoriteUserEntity(favoriteUser.id,favoriteUser.userId,favoriteUser.user?.let { user ->  UserEntity().fromUser(user) }))
    }

    override fun getAll(): Flow<List<FavoriteUser>> {
        return daoFavoriteUser.getAll().map {list->
            list.map {
                FavoriteUser(
                    it.id,
                    it.userId,
                    it.userEntity?.toUser()
                )
            }
        }
    }

    override fun getToSelect(): Flow<List<FavoriteUser>> {
        return daoFavoriteUser.getToUse().map {list-> list.map {
            FavoriteUser(
                it.id,
                it.userId,
                it.userEntity?.toUser()
            )
        }}
    }

    override fun deleteByUserId(id: Int) {
        daoFavoriteUser.deleteByUserId(id)
    }
}