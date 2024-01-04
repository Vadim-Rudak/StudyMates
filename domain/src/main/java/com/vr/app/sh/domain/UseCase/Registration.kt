package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Reg
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.repository.internet.DoorInSystemRepo
import java.io.File

class Registration(private val doorInSystemRepo: DoorInSystemRepo) {
    suspend fun execute(user: User, fileUserPhoto: File): Reg {
        return doorInSystemRepo.registration(user,fileUserPhoto)
    }
}