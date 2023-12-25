package com.vr.app.sh.domain.UseCase

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.TextUtils
import com.vr.app.sh.domain.model.AuthorizationEntity
import com.vr.app.sh.domain.repository.internet.DoorInSystemRepo
import okhttp3.RequestBody

class Authorization(private val doorInSystemRepo: DoorInSystemRepo) {
    suspend fun execute(userInfo: RequestBody): AuthorizationEntity {
        return if (!TextUtils.isEmpty(userInfo.toString())){
            doorInSystemRepo.Authorization(userInfo)
        }else{
            AuthorizationEntity(false,"Ошибка, заполните информацию о пользователе",null)
        }
    }
}