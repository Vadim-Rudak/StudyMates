package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.data.model.User
import com.vr.app.sh.domain.repository.UserRepo

class SaveUser(private val userRepo: UserRepo) {
    suspend fun execute(user: User){
        userRepo.save(user)
    }
}