package com.vr.app.sh.domain.repository.local.user

import com.vr.app.sh.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    suspend fun insertUsers(listUsers:List<User>)

    fun getAllUsers(): Flow<List<User>>
}