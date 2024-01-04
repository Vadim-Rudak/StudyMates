package com.vr.app.sh.domain.repository.internet

import com.vr.app.sh.domain.model.AuthorizationEntity
import com.vr.app.sh.domain.model.Reg
import com.vr.app.sh.domain.model.User
import java.io.File

interface DoorInSystemRepo {
    suspend fun authorization(login: String, password: String): AuthorizationEntity
    suspend fun registration(user: User, fileUserPhoto: File): Reg
}