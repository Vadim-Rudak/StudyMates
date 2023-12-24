package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.UserRepo

class CleanUser(private val userRepo: UserRepo) {
    fun execute(){
        userRepo.clear()
    }
}