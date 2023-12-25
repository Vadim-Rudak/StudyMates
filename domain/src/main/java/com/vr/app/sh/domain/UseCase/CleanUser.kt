package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.local.UserRepo

class CleanUser(private val userRepo: UserRepo) {
    fun execute(){
        userRepo.clear()
    }
}