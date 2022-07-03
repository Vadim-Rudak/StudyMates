package com.vr.app.sh.domain.repository

import com.vr.app.sh.domain.model.Auth
import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.model.Reg
import okhttp3.RequestBody
import retrofit2.Response

interface DoorInSystemRepo {
    suspend fun Authorization(requestBody: RequestBody): Auth
    suspend fun Registration(requestBody: RequestBody) : Reg
}