package com.vr.app.sh.domain.repository.internet

import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.model.response.ListResponse

interface UserInternetRepo {

    suspend fun getAllUsers(): ListResponse<User>

}