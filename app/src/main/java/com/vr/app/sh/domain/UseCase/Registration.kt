package com.vr.app.sh.domain.UseCase

import android.text.TextUtils
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.domain.model.Reg
import com.vr.app.sh.domain.repository.DoorInSystemRepo
import okhttp3.MultipartBody
import okhttp3.RequestBody

class Registration(private val doorInSystemRepo: DoorInSystemRepo) {
    suspend fun execute(userInfo: RequestBody,user_photo: MultipartBody.Part?):Reg{
        return doorInSystemRepo.Registration(userInfo,user_photo)
//        if (!TextUtils.isEmpty(userInfo.toString())){
//            return doorInSystemRepo.Registration(userInfo)
//        }else{
//            return Reg("Заполните все поля",false)
//        }
    }
}