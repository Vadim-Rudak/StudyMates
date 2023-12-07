package com.vr.app.sh.domain.repository

import com.vr.app.sh.data.model.User

interface UserRepo {

    fun save(user: User)
    fun clear()
}