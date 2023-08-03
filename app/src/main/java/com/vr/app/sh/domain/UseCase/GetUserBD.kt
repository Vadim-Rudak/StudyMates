package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.data.model.User
import com.vr.app.sh.data.repository.DAOUser

class GetUserBD(private val daoUser: DAOUser) {
    fun execute(): User {
        return daoUser.getUser()[0]
    }
}