package com.vr.app.sh.domain.repository.local

import com.vr.app.sh.domain.model.User

interface UserRepo {

    fun save(user: User)
    fun clear()
}