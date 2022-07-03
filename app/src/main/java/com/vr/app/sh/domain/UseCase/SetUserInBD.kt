package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.repository.UserRepo

class SetUserInBD(private val userRepo: UserRepo) {
    fun execute(user:User){
        userRepo.saveUserInDB(user)
    }
}