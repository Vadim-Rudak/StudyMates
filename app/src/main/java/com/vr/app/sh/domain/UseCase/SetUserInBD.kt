package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.data.model.User
import com.vr.app.sh.data.repository.DAOUser

class SetUserInBD(private val daoUser: DAOUser) {
    suspend fun execute(user: User){
        daoUser.saveUserNow(user = user)
    }
}