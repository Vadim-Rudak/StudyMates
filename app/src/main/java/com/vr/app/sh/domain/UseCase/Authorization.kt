package com.vr.app.sh.domain.UseCase

import android.content.Context
import android.text.TextUtils
import com.vr.app.sh.domain.model.Auth
import com.vr.app.sh.domain.repository.DoorInSystemRepo
import okhttp3.RequestBody

class Authorization(private val doorInSystemRepo: DoorInSystemRepo) {
    suspend fun execute(userInfo: RequestBody): Auth {
        if (!TextUtils.isEmpty(userInfo.toString())){
            return doorInSystemRepo.Authorization(userInfo)
        }else{
            return Auth(false,"Ошибка, заполните информацию о пользователе",null)
        }
    }
}