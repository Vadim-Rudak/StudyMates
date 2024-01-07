package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.AuthorizationEntity
import com.vr.app.sh.domain.repository.internet.DoorInSystemRepo

class Authorization(private val doorInSystemRepo: DoorInSystemRepo) {
    suspend fun execute(login: String, password: String): AuthorizationEntity {
        return if (login.trim().isNotEmpty()&&password.trim().isNotEmpty()){
            doorInSystemRepo.authorization(login, password)
        }else{
            AuthorizationEntity(false,"Ошибка, заполните информацию о пользователе")
        }
    }
}