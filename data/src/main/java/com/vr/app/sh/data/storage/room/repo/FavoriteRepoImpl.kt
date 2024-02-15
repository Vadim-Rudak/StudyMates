package com.vr.app.sh.data.storage.room.repo

import com.vr.app.sh.data.storage.model.chat.FavoriteUserEntity
import com.vr.app.sh.data.storage.model.users.UserEntity
import com.vr.app.sh.data.storage.room.dao.DAOFavoriteUser
import com.vr.app.sh.domain.model.messages.FavoriteUser
import com.vr.app.sh.domain.repository.local.chat.FavoriteRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepoImpl(private var daoFavoriteUser: DAOFavoriteUser) : FavoriteRepo {
    override suspend fun insert(favoriteUser: FavoriteUser) {
        val user = favoriteUser.user
        daoFavoriteUser.insert(
            FavoriteUserEntity(
                id = favoriteUser.id,
                userId = favoriteUser.userId,
                userEntity = user?.let {
                    UserEntity(
                        it.id,
                        it.name,
                        it.lastName,
                        it.gender,
                        it.dateBirthday,
                        it.cityLive
                    )
                }
            )
        )
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

}