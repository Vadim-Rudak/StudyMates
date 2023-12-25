package com.vr.app.sh.domain.repository.internet

import com.vr.app.sh.domain.model.Auth
import com.vr.app.sh.domain.model.AuthorizationEntity
import com.vr.app.sh.domain.model.Reg
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface DoorInSystemRepo {
    suspend fun Authorization(requestBody: RequestBody): AuthorizationEntity
    suspend fun Registration(requestBody: RequestBody,user_photo: MultipartBody.Part?): Reg
}