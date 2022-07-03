package com.vr.app.sh.domain.repository

import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.model.User

interface UserRepo {
    fun getUser(): User
    fun saveUserInDB(user:User)
}