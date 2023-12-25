package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.repository.local.UserRepo

class SaveUser(private val userRepo: UserRepo) {
    fun execute(user: User){
        userRepo.save(user)
    }
}