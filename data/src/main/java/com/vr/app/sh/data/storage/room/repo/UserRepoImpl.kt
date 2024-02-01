package com.vr.app.sh.data.storage.room.repo

import com.vr.app.sh.data.storage.model.users.UserEntity
import com.vr.app.sh.data.storage.room.dao.user.DAOUser
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.repository.local.user.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepoImpl(private val daoUser: DAOUser):UserRepo {
    override suspend fun insertUsers(listUsers: List<User>) {
        daoUser.insertUsers(listUsers.map { UserEntity(
            id = it.id,
            name = it.name,
            lastName = it.lastName,
            gender = it.gender,
            dateBirthday = it.dateBirthday,
            cityLive = it.cityLive
        )})
    }

    override fun getAllUsers(): Flow<List<User>> {
        return daoUser.getAllUsers().map {
            it.map {userEntity->
                userEntity.toUser()
            }
        }
    }
}